package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.StateMachine.Enums.*;
import frc.robot.StateMachine.States.*;

public class Actions extends SequentialCommandGroup {

public static int counter = 0;

    public Actions() {
        super(
            commands(
                init(),
                main(),
                finish())
            );
    }

    private static ArrayList<Command> main() {
        ArrayList<Command> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(new OdometryREL(0, 50, 0, true, true, EnumLift.TOP, EnumRotation.IDLE, EnumGripper.OPEN));
        }
        return list;
    }

    private static Command[] commands(ArrayList<Command>... values) {
        ArrayList<Command> collectedCommands = new ArrayList<>();
        for (ArrayList<Command> n: values) {
            collectedCommands.addAll(n);
        }
        Command[] outArray = new Command[collectedCommands.size()];
        return collectedCommands.toArray(outArray);
    }

    private static ArrayList<Command> init() {
        ArrayList<Command> list = new ArrayList<>();
        list.add(new Start());
        list.add(new ResetXYZ(0,0,0));
        return list;
    }

    private static ArrayList<Command> finish() {
        ArrayList<Command> list = new ArrayList<>();
        list.add(new Finish());
        return list;
    }

    private static double startTime = Timer.getFPGATimestamp();
    public static double iterationTime(boolean reset) {
        if (reset) {
            startTime = Timer.getFPGATimestamp();
        }
        return Timer.getFPGATimestamp() - startTime;
    }
}
