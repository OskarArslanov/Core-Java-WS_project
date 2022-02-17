package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.StateMachine.CoreEngine.StateMachine;
import frc.robot.StateMachine.CoreEngine.CommandAdapter;

public class Robot extends TimedRobot {

  private Command auto = new CommandAdapter();

  @Override
  public void robotInit() {
    initMaps();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    if (Main.switchMap.get("resetButtonStatus")) {
      StateMachine.firstIteration = true;
      StateMachine.index = 0;
    }
    sendSmartDashBoard();
  }
  
  @Override
  public void disabledInit() {
    if (auto != null) {
      auto.cancel();
    }
  }

  @Override
  public void autonomousInit() {
    if (auto != null) {
      auto.schedule();
    }
  }

  @Override 
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    if (auto != null) {
      auto.cancel();
    }
  }

  @Override
  public void testInit() {
 
  }

  private void initMaps() {
    Main.switchMap.put("startButtonStatus", false);
    Main.switchMap.put("resetButtonStatus", false);
    Main.switchMap.put("stopButtonStatus", false);
    Main.switchMap.put("EMSButtonStatus", false);
    Main.switchMap.put("LS_Top", false);
    Main.switchMap.put("LS_Bottom", false);
    Main.sensorsMap.put("sharpLeft", 0.0);
    Main.sensorsMap.put("sharpRight", 0.0);
    Main.sensorsMap.put("sonarLeft", 0.0);
    Main.sensorsMap.put("sonarRight", 0.0);
    Main.sensorsMap.put("gyro", 0.0);
    Main.sensorsMap.put("resetGyro", 0.0);
    Main.sensorsMap.put("updateTime", 0.0);
    Main.motorControllerMap.put("encDistance0", 0.0);
    Main.motorControllerMap.put("encDistance1", 0.0);
    Main.motorControllerMap.put("encDistance2", 0.0);
    Main.motorControllerMap.put("encDistance3", 0.0);
    Main.motorControllerMap.put("resetEncs", 0.0);
    Main.motorControllerMap.put("getMotorSpeed0", 0.0);
    Main.motorControllerMap.put("getMotorSpeed1", 0.0);
    Main.motorControllerMap.put("getMotorSpeed2", 0.0);
    Main.motorControllerMap.put("getMotorSpeed3", 0.0);
    Main.motorControllerMap.put("setMotorSpeed0", 0.0);
    Main.motorControllerMap.put("setMotorSpeed1", 0.0);
    Main.motorControllerMap.put("setMotorSpeed2", 0.0);
    Main.motorControllerMap.put("setMotorSpeed3", 0.0);
    Main.motorControllerMap.put("updateTime", 0.0);
  }

  private void sendSmartDashBoard() {
    SmartDashboard.putBoolean("isEMS", Main.switchMap.get("EMSButtonStatus"));
    SmartDashboard.putBoolean("isReset", Main.switchMap.get("resetButtonStatus"));
    SmartDashboard.putBoolean("isStop", Main.switchMap.get("stopButtonStatus"));
    SmartDashboard.putBoolean("isStart", Main.switchMap.get("startButtonStatus"));
    SmartDashboard.putNumber("sharpLeft", Main.sensorsMap.get("sharpLeft"));
    SmartDashboard.putNumber("sharpRight", Main.sensorsMap.get("sharpRight"));
    SmartDashboard.putNumber("sonarLeft", Main.sensorsMap.get("sonarLeft"));
    SmartDashboard.putNumber("sonarRight", Main.sensorsMap.get("sonarRight"));
    SmartDashboard.putNumber("gyro", Main.sensorsMap.get("gyro"));
    SmartDashboard.putNumber("encDistance0", Main.motorControllerMap.get("encDistance0"));
    SmartDashboard.putNumber("index", StateMachine.index);
    SmartDashboard.putNumber("updateTimeMotors", Main.motorControllerMap.get("updateTime"));
    SmartDashboard.putNumber("updateTimeSensors", Main.sensorsMap.get("updateTime"));
  }
}
