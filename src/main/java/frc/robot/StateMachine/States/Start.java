package frc.robot.StateMachine.States;

import frc.robot.Main;
import frc.robot.StateMachine.CoreEngine.IState;

public class Start implements IState {

    @Override
    public void initialize() {
        Main.sensorsMap.put("resetZ", 1.0);
    }

    @Override
    public void execute() {
        Main.motorControllerMap.put("speedX", 0.0);
        Main.motorControllerMap.put("speedY", 0.0);
        Main.motorControllerMap.put("speedZ", 0.0);
        
    }

    @Override
    public void finilize() {
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
