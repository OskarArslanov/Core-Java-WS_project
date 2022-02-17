package frc.robot.StateMachine.CoreEngine;

import frc.robot.subsystems.StationController;

public interface IState { //required stages and imports for states

    public static final StationController STATION_CONTROLLER = new StationController();

    void initialize();
    void execute();
    void finilize();
    boolean isFinished();
    
}
