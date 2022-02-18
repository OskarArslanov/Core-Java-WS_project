package frc.robot.StateMachine.CoreEngine;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.StateMachine.States.ResetPos;
import frc.robot.StateMachine.Enums.EnumGripper;
import frc.robot.StateMachine.Enums.EnumLift;
import frc.robot.StateMachine.Enums.EnumRotation;
import frc.robot.StateMachine.States.Finish;
import frc.robot.StateMachine.States.OdometryABS;
import frc.robot.StateMachine.States.Start;

public class StateMachine { // inner class that adapts Oskar's states to CommandBase

    public static int index = 0;
    private IState currentState;
    public static boolean firstIteration;
    public static double iterationTime;

    public static ArrayList<IState> states = new ArrayList<>();

    public void initStates() { // add actions here
        firstIteration = true;
        states.add(new Start());
        states.add(new ResetPos(0,0,0));
        states.add(new OdometryABS(0, 50, 0, true, true, EnumLift.IDLE, EnumRotation.IDLE, EnumGripper.IDLE));
        states.add(new Finish());
    }

    private static double startTime;
    public void executeStates() {
        if (firstIteration) { // init stage of every state
            startTime = Timer.getFPGATimestamp();
            firstIteration = false;
            currentState = states.get(index);
            currentState.initialize();
        }

        currentState.execute(); // execute stage of every state

        if (currentState.isFinished()) { // finish stage of every state
            firstIteration = true;
            StateMachine.index++;
            currentState.finilize();
        }
        iterationTime = Timer.getFPGATimestamp() - startTime;
    }    

    public boolean isProgramFinished() {
        return currentState.getClass().getName() == "Finish";
    }
}
