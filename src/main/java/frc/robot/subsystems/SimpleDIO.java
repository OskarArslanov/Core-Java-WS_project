package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

public abstract class SimpleDIO {
    private static final DigitalOutput redLamp = new DigitalOutput(8);
    private static final DigitalOutput greenLamp = new DigitalOutput(9);
    private static final DigitalInput startButton = new DigitalInput(10);
    private static final DigitalInput emsButton = new DigitalInput(11);    

    public static void setRedLamp(boolean state){
        redLamp.set(state);
    }

    public static void setGreenLamp(boolean state){
        greenLamp.set(state);
    }

    public static boolean isStart() {
        return !startButton.get();
    }

    public static boolean isEMS() {
        return emsButton.get();
    }
    
}
