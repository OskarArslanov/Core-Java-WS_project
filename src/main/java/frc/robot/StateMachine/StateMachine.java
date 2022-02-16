package frc.robot.StateMachine;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.StateMachine.States.StateController;

public class StateMachine extends CommandBase {

    StateController stateController = new StateController();

    @Override
    public void initialize() {
        stateController.initStates();
    }

    @Override
    public void execute() {
        stateController.executeStates();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return stateController.isProgramFinished();
    }
}