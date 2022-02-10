package frc.robot.StateMachine.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.StateMachine.Enums.*;

public class Sensors extends CommandBase {
    private double set0 = 0;
    private double set1 = 0;
    private double set2 = 0;
    private boolean set3 = false;
    private boolean set4 = false;
    private EnumLift enumLift = EnumLift.IDLE;
    private EnumRotation enumRotation = EnumRotation.IDLE;
    private EnumGripper enumGripper = EnumGripper.IDLE;

    public Sensors(double set0, double set1, double set2, boolean set3, boolean set4, EnumLift enumLift, EnumRotation enumRotation, EnumGripper enumGripper) {
        this.set0 = set0;
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.enumLift = enumLift;
        this.enumRotation = enumRotation;
        this.enumGripper = enumGripper;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    }

    @Override
    public void end (boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
