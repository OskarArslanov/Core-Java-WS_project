package frc.robot.StateMachine.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Actions;
import frc.robot.Robot;
import frc.robot.StateMachine.Enums.*;

public class OdometryABS extends CommandBase{
    private double set0 = 0;
    private double set1 = 0;
    private double set2 = 0;
    private boolean set3 = false;
    private boolean set4 = false;
    private EnumLift enumLift = EnumLift.IDLE;
    private EnumRotation enumRotation = EnumRotation.IDLE;
    private EnumGripper enumGripper = EnumGripper.IDLE;

    public OdometryABS(double set0, double set1, double set2, boolean set3, boolean set4, EnumLift enumLift, EnumRotation enumRotation, EnumGripper enumGripper) {
        this.set0 = set0;
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.enumLift = enumLift;
        this.enumRotation = enumRotation;
        this.enumGripper = enumGripper;
    }

    public void disableXYZ() {
        if (set0 == -1) {
            set0 = Robot.holonomicDrive.getPositionX();
        }
        if (set1 == -1) {
            set1 = Robot.holonomicDrive.getPositionY();
        }
        if (set2 == -1) {
            set2 = Robot.holonomicDrive.getPositionZ();
        }
    }

    @Override
    public void initialize() {
        disableXYZ();
        Actions.iterationTime(true);
    }

    @Override
    public void execute() {
        Robot.holonomicDrive.distanceToSpeed(set0, set1, set2, set3, set4);
    }

    @Override
    public void end (boolean interrupted) {
        if (this.set4) {
            Robot.holonomicDrive.setSpeed(0, 0, 0);
        }
        Actions.iterationTime(true);
        Actions.counter++;
    }

    @Override
    public boolean isFinished() {
        return Robot.holonomicDrive.isFinish();
    }
}
