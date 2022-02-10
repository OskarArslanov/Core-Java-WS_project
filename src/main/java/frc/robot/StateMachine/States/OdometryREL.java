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

        double sinX = Math.sin (Math.toRadians(-initZ)) * this.set0; // add to outX
        double cosX = Math.cos (Math.toRadians(-initZ)) * this.set0; // add to outY
        double sinY = Math.sin (Math.toRadians(initZ+90)) * this.set1; // add to outX
        double cosY = Math.cos (Math.toRadians(initZ+90)) * this.set1; // add to outY

        double convY = sinX + sinY;
        double convX = cosX - cosY;

        this.set0 = initX + convX;
        this.set1 = initY + convY;
        this.set2 = initZ + this.set2;
    }
 
    @Override
    public void initialize() {
        firstIterationXYZ();
        relativeDirectionPositioning();
        Actions.iterationTime(true);
    }

    @Override
    public void execute() {
        Robot.holonomicDrive.distanceToSpeed(this.set0, this.set1, this.set2, this.set3, this.set4);
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
