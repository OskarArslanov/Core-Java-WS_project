package frc.robot.StateMachine.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Actions;
import frc.robot.Robot;
import frc.robot.StateMachine.Enums.*;

public class OdometryREL extends CommandBase {
    private double set0 = 0;
    private double set1 = 0;
    private double set2 = 0;
    private boolean set3 = false;
    private boolean set4 = false;
    private EnumLift enumLift = EnumLift.IDLE;
    private EnumRotation enumRotation = EnumRotation.IDLE;
    private EnumGripper enumGripper = EnumGripper.IDLE;
    private double initX;
    private double initY;
    private double initZ;

    public OdometryREL(double set0, double set1, double set2, boolean set3, boolean set4, EnumLift enumLift, EnumRotation enumRotation, EnumGripper enumGripper) {
        this.set0 = set0;
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.enumLift = enumLift;
        this.enumRotation = enumRotation;
        this.enumGripper = enumGripper;
    }

    private void firstIterationXYZ() {
        initX = Robot.holonomicDrive.getPositionX();
        initY = Robot.holonomicDrive.getPositionY();
        initZ = Robot.holonomicDrive.getPositionZ();
    }

    private void relativeDirectionPositioning() {
        double angleRelAxis90 = Math.toRadians(set2 + 90);
        double angleRelAxis0 = Math.toRadians(set2);
        double sinRelAxisX = Math.sin(angleRelAxis90) * set0;
        double sinRelAxisY = Math.sin(angleRelAxis0) * set1;
        double cosRelAxisX = Math.cos(angleRelAxis90) * set0;
        double cosRelAxisY = Math.cos(angleRelAxis0) * set1;
        set0 = sinRelAxisX + sinRelAxisY + initX;
        set1 = cosRelAxisX + cosRelAxisY + initY;
        set2 = set2 + initZ;
    }

    @Override
    public void initialize() {
        firstIterationXYZ();
        relativeDirectionPositioning();
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
        Actions.counter++;
        Actions.iterationTime(true);
    }

    @Override
    public boolean isFinished() {
        return Robot.holonomicDrive.isFinish();
    }
}
