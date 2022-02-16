package frc.robot.StateMachine.States;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Actions;

public class Start extends SequentialCommandGroup {

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    }

    @Override
    public void end (boolean interrupted) {
        Actions.counter++;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
