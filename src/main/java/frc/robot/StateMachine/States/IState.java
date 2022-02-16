package frc.robot.StateMachine.States;

public interface IState {

    void initialize();
    void execute();
    void finilize();
    boolean isFinished();
    
}
