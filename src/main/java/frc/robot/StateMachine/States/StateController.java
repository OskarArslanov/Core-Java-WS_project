package frc.robot.StateMachine.States;

import java.util.ArrayList;

public class StateController {

    public static int index = 0;
    private static IState currentState;
    private static boolean firstIteration;


    public static ArrayList<IState> states = new ArrayList<>();

    public void initStates() {
        firstIteration = true;
        states.add(new Start());
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
            StateController.index++;
            currentState.finilize();
        }
    }    

    public boolean isProgramFinished() {
        return currentState.getClass().getName() == "Finish";
    }
}
