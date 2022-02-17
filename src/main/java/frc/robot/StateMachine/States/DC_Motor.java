package frc.robot.StateMachine.States;

import frc.robot.Main;
import frc.robot.StateMachine.CoreEngine.IState;

public class DC_Motor implements IState {

    @Override
    public void initialize() {
        Main.motorControllerMap.put("resetEncs", 1.0);
    }

    @Override
    public void execute() {
        Main.motorControllerMap.put("resetEncs", 0.0);
        Main.motorControllerMap.put("setMotorSpeed0", 1.0);
    }

    @Override
    public void finilize() {
        Main.motorControllerMap.put("setMotorSpeed0", 0.0);
    }

    @Override
    public boolean isFinished() {
        return Main.motorControllerMap.get("encDistance0") > 400;
    }  
}