package frc.robot.StateMachine.States;

import frc.robot.Main;
import frc.robot.StateMachine.CoreEngine.IState;

public class Finish implements IState {

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute() {
        Main.motorControllerMap.put("speedX", 0.0);
        Main.motorControllerMap.put("speedY", 0.0);
        Main.motorControllerMap.put("speedZ", 0.0);
        
    }

    @Override
    public void finilize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
