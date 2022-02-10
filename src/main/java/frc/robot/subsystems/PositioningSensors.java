package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Timer;
import com.studica.frc.Cobra;

public abstract class PositioningSensors {
    private static final AnalogInput sharpL = new AnalogInput(1);
    private static final AnalogInput sharpR = new AnalogInput(0);
    private static final Ultrasonic sonicL = new Ultrasonic(8, 9);
    private static final Ultrasonic sonicR = new Ultrasonic(10, 11);
    private static final Cobra cobra = new Cobra();

    public double getSharpLeft() {
        return (Math.pow(sharpL.getAverageVoltage(), -1.2045) * 27.726);
    }

    public double getSharpRight() {
        return (Math.pow(sharpR.getAverageVoltage(), -1.2045) * 27.726);
    }

    public double getSonicRight() {
        sonicR.ping();
        Timer.delay(0.005);
        return sonicR.getRangeMM();
    }

    public double getSonicLeft() {
        sonicL.ping();
        Timer.delay(0.005);
        return sonicL.getRangeMM();
    }

    public double getCobraVoltage(int channel) {
        return cobra.getVoltage(channel);
    }
}
