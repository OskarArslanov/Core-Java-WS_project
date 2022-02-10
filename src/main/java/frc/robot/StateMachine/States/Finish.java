package frc.robot.StateMachine.States;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class Finish extends CommandBase {

    @Override
    public void initialize() {
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(0, 0);
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(1, 0);
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(2, 0);
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(3, 0);
    }

    @Override
    public void execute() {
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(0, 0);
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(1, 0);
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(2, 0);
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(3, 0);
    }

    @Override
    public void end (boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }  
}
