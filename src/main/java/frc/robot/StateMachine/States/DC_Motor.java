package frc.robot.StateMachine.States;

import frc.robot.StateMachine.CoreEngine.IState;

public class DC_Motor implements IState {

    @Override
    public void initialize() {
        STATION_CONTROLLER.resetEnc();      
    }

    @Override
    public void execute() {
        STATION_CONTROLLER.setPWM(1);
        
    }

    @Override
    public void finilize() {
        STATION_CONTROLLER.setPWM(0);
        
    }

    @Override
    public boolean isFinished() {
        return STATION_CONTROLLER.getEncoderDistance() > 100;
    }
    
    
}
