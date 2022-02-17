package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.StateMachine.CoreEngine.IState;
import frc.robot.StateMachine.CoreEngine.StateMachine;
import frc.robot.StateMachine.CoreEngine.CommandAdapter;
import frc.robot.subsystems.StationController;

public class Robot extends TimedRobot {

  private Command auto = new CommandAdapter();

  @Override
  public void robotInit() {
    IState.STATION_CONTROLLER.resetEnc();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    if (IState.STATION_CONTROLLER.getResetButtonStatus()) {
      StateMachine.firstIteration = true;
      StateMachine.index = 0;
      IState.STATION_CONTROLLER.resetEnc();
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

  private void sendSmartDashBoard() {
    SmartDashboard.putBoolean("isEMS", IState.STATION_CONTROLLER.getEMS_ButtonStatus());
    SmartDashboard.putBoolean("isStart", IState.STATION_CONTROLLER.getStartButtonStatus());
    SmartDashboard.putBoolean("isReset", IState.STATION_CONTROLLER.getResetButtonStatus());
    SmartDashboard.putBoolean("isStop", IState.STATION_CONTROLLER.getStopButtonStatus());
    SmartDashboard.putNumber("sharp", IState.STATION_CONTROLLER.getSharpDistance());
    SmartDashboard.putNumber("sonar", IState.STATION_CONTROLLER.getSonarDistance());
    SmartDashboard.putNumber("encoder", IState.STATION_CONTROLLER.getEncoderDistance());
    SmartDashboard.putNumber("index", StateMachine.index);
  }
}
