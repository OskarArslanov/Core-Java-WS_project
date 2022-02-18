package frc.robot.StateMachine.CoreEngine;

public interface IState { //required stages for states
    void initialize();
    void execute();
    void finilize();
    boolean isFinished();
    
}
