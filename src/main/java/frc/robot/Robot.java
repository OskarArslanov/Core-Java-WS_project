package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.UserMath.HolonomicDrive;
import frc.robot.subsystems.MobilityControl;
import frc.robot.subsystems.SimpleDIO;

public class Robot extends TimedRobot {

  public static HolonomicDrive holonomicDrive = new HolonomicDrive();
  private Command auto = new Actions();

  private final static Runnable mobilityJob = new MobilityControl();
  private final static Thread mobilityThread = new Thread(mobilityJob);

  @Override
  public void robotInit() {
    mobilityThread.start();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    sendDataToShuffleboard();    
    if (SimpleDIO.isEMS()) {
      holonomicDrive.pid_Regulator0.reset();
      holonomicDrive.pid_Regulator1.reset();
      holonomicDrive.pid_Regulator2.reset();
      holonomicDrive.pid_Regulator3.reset();
      Robot.holonomicDrive.dc_Motors.setMotorSpeed(0, 0);
      Robot.holonomicDrive.dc_Motors.setMotorSpeed(1, 0);
      Robot.holonomicDrive.dc_Motors.setMotorSpeed(2, 0);
      Robot.holonomicDrive.dc_Motors.setMotorSpeed(3, 0);
    }
  }
  
  @Override
  public void disabledInit() {
    holonomicDrive.gyroscope.resetGyro();
    holonomicDrive.pid_Regulator0.reset();
    holonomicDrive.pid_Regulator1.reset();
    holonomicDrive.pid_Regulator2.reset();
    holonomicDrive.pid_Regulator3.reset();
    if (auto != null) {
      auto.cancel();
    }
  }

  @Override
  public void autonomousInit() {
    holonomicDrive.gyroscope.resetGyro();
    holonomicDrive.pid_Regulator0.reset();
    holonomicDrive.pid_Regulator1.reset();
    holonomicDrive.pid_Regulator2.reset();
    holonomicDrive.pid_Regulator3.reset();
    Actions.counter = 0;
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
    holonomicDrive.gyroscope.resetGyro();
    holonomicDrive.pid_Regulator0.reset();
    holonomicDrive.pid_Regulator1.reset();
    holonomicDrive.pid_Regulator2.reset();
    holonomicDrive.pid_Regulator3.reset();  
  }

  private void sendDataToShuffleboard() {
    SmartDashboard.putNumber("encLeft", holonomicDrive.dc_Motors.getEncDistance(0));
    SmartDashboard.putNumber("encBack", holonomicDrive.dc_Motors.getEncDistance(1));
    SmartDashboard.putNumber("encRight", holonomicDrive.dc_Motors.getEncDistance(2));
    SmartDashboard.putNumber("velLeft", holonomicDrive.dc_Motors.getEncSpeed(0));
    SmartDashboard.putNumber("velBack", holonomicDrive.dc_Motors.getEncSpeed(1));
    SmartDashboard.putNumber("velRight", holonomicDrive.dc_Motors.getEncSpeed(2));
    SmartDashboard.putNumber("speedX", HolonomicDrive.speeds[0]);
    SmartDashboard.putNumber("speedY", HolonomicDrive.speeds[1]);
    SmartDashboard.putNumber("speedZ", HolonomicDrive.speeds[2]);
    SmartDashboard.putNumber("positionX", holonomicDrive.getPositionX());
    SmartDashboard.putNumber("positionY", holonomicDrive.getPositionY());
    SmartDashboard.putNumber("positionZ", holonomicDrive.getPositionZ());
    SmartDashboard.putNumber("upDateTimeMobility", MobilityControl.upDateTime);
    SmartDashboard.putNumber("Action", Actions.counter);
    SmartDashboard.putNumber("IterationTime", Actions.iterationTime(false));
    SmartDashboard.putNumber("pidLeft", holonomicDrive.pid_Regulator0.getOutput());  
    SmartDashboard.putNumber("pidBack", holonomicDrive.pid_Regulator1.getOutput());  
    SmartDashboard.putNumber("pidRight", holonomicDrive.pid_Regulator2.getOutput());  

  }
}
