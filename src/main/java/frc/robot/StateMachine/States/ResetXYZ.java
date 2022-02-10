package frc.robot.StateMachine.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Actions;
import frc.robot.Robot;

public class ResetXYZ extends CommandBase {
    private double set0 = 0;
    private double set1 = 0;
    private double set2 = 0;

    public ResetXYZ(double set0, double set1, double set2) {
        this.set0 = set0;
        this.set1 = set1;
        this.set2 = set2;
    }

    @Override
    public void initialize() {
        Robot.holonomicDrive.setPosition(set0, set1, set2);
    }

    @Override
    public void execute() {
        Robot.holonomicDrive.setPosition(set0, set1, set2);
    }

    @Override
    public void end (boolean interrupted) {
        Actions.counter++;
        Robot.holonomicDrive.setPosition(set0, set1, set2);
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(0, 0);
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(1, 0);
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(2, 0);
        Robot.holonomicDrive.dc_Motors.setMotorSpeed(3, 0);
    }

    @Override
    public boolean isFinished() {
        Robot.holonomicDrive.setPosition(set0, set1, set2);
        return true;
    }
}
