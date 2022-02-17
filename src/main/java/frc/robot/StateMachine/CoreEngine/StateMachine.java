package frc.robot.StateMachine.CoreEngine;

import java.util.ArrayList;

import frc.robot.StateMachine.States.DC_Motor;
import frc.robot.StateMachine.States.Finish;
import frc.robot.StateMachine.States.Start;

public class StateMachine { // inner class that adapts Oskar's states to CommandBase

    public static int index = 0;
    private static IState currentState;
    public static boolean firstIteration;

    public static ArrayList<IState> states = new ArrayList<>();

    public void initStates() { // add actions here
        firstIteration = true;
        states.add(new Start());
        states.add(new DC_Motor());
        states.add(new Finish());
    }

    public void executeStates() {
        if (firstIteration) {
            firstIteration = false;
            currentState = states.get(index);
            currentState.initialize();
        }

        currentState.execute();

        if (currentState.isFinished()) {
            firstIteration = true;
            StateMachine.index++;
            currentState.finilize();
        }
        
    }    

    public boolean isProgramFinished() {
        return currentState.getClass().getName() == "Finish";
    }
}
