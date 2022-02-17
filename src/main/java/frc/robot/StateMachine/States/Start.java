package frc.robot.StateMachine.States;

import frc.robot.Main;
import frc.robot.StateMachine.CoreEngine.IState;

public class Start implements IState {

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void finilize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isFinished() {
        return Main.switchMap.get("startButtonStatus");
    }

}
