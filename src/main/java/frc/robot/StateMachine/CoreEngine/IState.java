package frc.robot.StateMachine.CoreEngine;

public interface IState { //required stages and imports for states
    void initialize();
    void execute();
    void finilize();
    boolean isFinished();
    
}
