package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.StationController;

public class Robot extends TimedRobot {

  private Command auto = new Actions();

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
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
    SmartDashboard.putBoolean("isEMS", StationController.getEMS_ButtonStatus());
    SmartDashboard.putBoolean("isStart", StationController.getStartButtonStatus());
    SmartDashboard.putBoolean("isReset", StationController.getResetButtonStatus());
    SmartDashboard.putBoolean("isStop", StationController.getStopButtonStatus());
    SmartDashboard.putNumber("sharp", StationController.getSharpDistance());
    SmartDashboard.putNumber("sonar", StationController.getSonarDistance());

  }
}
