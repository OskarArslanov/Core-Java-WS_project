package frc.robot.StateMachine.CoreEngine;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CommandAdapter extends CommandBase { // outter class for CommandsBase. Includes states of Command

    // do not do anything here
    StateMachine stateMachine = new StateMachine();

    @Override
    public void initialize() {
        stateMachine.initStates();
    }

    @Override
    public void execute() {
        stateMachine.executeStates();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return stateMachine.isProgramFinished();
    }
}