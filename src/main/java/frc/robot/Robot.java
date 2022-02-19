package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.StateMachine.CoreEngine.StateMachine;
import frc.robot.Maths.Odometry.PositionController;
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
    PositionController.calculateXYZ();
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
  public void teleopPeriodic() {
    Main.motorControllerMap.put("speedX", 0.0);
    Main.motorControllerMap.put("speedY", 0.0);
    Main.motorControllerMap.put("speedZ", 0.0);
    Main.motorControllerMap.put("posX", 0.0);
    Main.motorControllerMap.put("posY", 0.0);
    Main.sensorsMap.put("posZ", 0.0);
    Main.sensorsMap.put("resetGyro", 0.0);
    Main.motorControllerMap.put("resetEncs", 1.0);
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
    Main.sensorsMap.put("updateTime", 0.0);
    Main.motorControllerMap.put("enc0", 0.0);
    Main.motorControllerMap.put("enc1", 0.0);
    Main.motorControllerMap.put("enc2", 0.0);
    Main.motorControllerMap.put("enc3", 0.0);
    Main.motorControllerMap.put("resetEncs", 0.0);
    Main.motorControllerMap.put("rpm0", 0.0);
    Main.motorControllerMap.put("rpm1", 0.0);
    Main.motorControllerMap.put("rpm2", 0.0);
    Main.motorControllerMap.put("rpm3", 0.0);
    Main.motorControllerMap.put("PID0", 0.0);
    Main.motorControllerMap.put("PID1", 0.0);
    Main.motorControllerMap.put("PID2", 0.0);
    Main.motorControllerMap.put("PID3", 0.0);
    Main.motorControllerMap.put("speedX", 0.0);
    Main.motorControllerMap.put("speedY", 0.0);
    Main.motorControllerMap.put("speedZ", 0.0);
    Main.motorControllerMap.put("posX", 0.0);
    Main.motorControllerMap.put("posY", 0.0);
    Main.sensorsMap.put("posZ", 0.0);
    Main.sensorsMap.put("resetGyro", 0.0);
    Main.motorControllerMap.put("updateTime", 0.0);
    Main.sensorsMap.put("srcGyro", 0.0);
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
    SmartDashboard.putBoolean("isResetZ", Main.sensorsMap.get("resetGyro") == 1.0);
    SmartDashboard.putBoolean("isResetEncs", Main.motorControllerMap.get("resetEncs") == 1.0);
    SmartDashboard.putNumber("rpm0", Main.motorControllerMap.get("rpm0"));
    SmartDashboard.putNumber("rpm1", Main.motorControllerMap.get("rpm1"));
    SmartDashboard.putNumber("rpm2", Main.motorControllerMap.get("rpm2"));
    SmartDashboard.putNumber("rpm3", Main.motorControllerMap.get("rpm3"));
    SmartDashboard.putNumber("enc0", Main.motorControllerMap.get("enc0"));
    SmartDashboard.putNumber("enc1", Main.motorControllerMap.get("enc1"));
    SmartDashboard.putNumber("enc2", Main.motorControllerMap.get("enc2"));
    SmartDashboard.putNumber("enc3", Main.motorControllerMap.get("enc3"));
    SmartDashboard.putNumber("PID0", Main.motorControllerMap.get("PID0"));
    SmartDashboard.putNumber("PID1", Main.motorControllerMap.get("PID1"));
    SmartDashboard.putNumber("PID2", Main.motorControllerMap.get("PID2"));
    SmartDashboard.putNumber("PID3", Main.motorControllerMap.get("PID3"));
    SmartDashboard.putNumber("speedX", Main.motorControllerMap.get("speedX"));
    SmartDashboard.putNumber("speedY", Main.motorControllerMap.get("speedY"));
    SmartDashboard.putNumber("speedZ", Main.motorControllerMap.get("speedZ"));
    SmartDashboard.putNumber("posX", Main.motorControllerMap.get("posX"));
    SmartDashboard.putNumber("posY", Main.motorControllerMap.get("posY"));
    SmartDashboard.putNumber("posZ", Main.sensorsMap.get("posZ"));
    SmartDashboard.putNumber("index", StateMachine.index);
    SmartDashboard.putNumber("updateTimeMotors", Main.motorControllerMap.get("updateTime"));
    SmartDashboard.putNumber("updateTimeSensors", Main.sensorsMap.get("updateTime"));
    if (StateMachine.states.size() > 0) {
      SmartDashboard.putString("currentState", StateMachine.currentState.getClass().getSimpleName());
    } else {
      SmartDashboard.putString("currentState", "null");
    }
  }
}
