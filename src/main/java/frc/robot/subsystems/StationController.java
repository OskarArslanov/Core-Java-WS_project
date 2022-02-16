package frc.robot.subsystems;

import com.studica.frc.TitanQuad;
import com.studica.frc.TitanQuadEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class StationController {
    
    // private static final TitanQuad dcMotor = new TitanQuad(42, 0);
    // public static void setPWM(double value) {
    //     dcMotor.set(value);
    // }

    // private static final TitanQuadEncoder enc = new TitanQuadEncoder(dcMotor, 0, 1);
    // public static double getDC_MotorSpeed() {
    //     return enc.getSpeed();
    // }

    // public static double getDC_MotorDistance() {
    //     return enc.getEncoderDistance();
    // }

    private static final DigitalInput emsButton = new DigitalInput(12); 
    public static boolean getEMS_ButtonStatus() {
        return emsButton.get();
    }

    private static final DigitalInput startButton = new DigitalInput(13);
    public static boolean getStartButtonStatus() {
        return startButton.get();
    }

    private static final DigitalInput resetButton = new DigitalInput(14);
    public static boolean getResetButtonStatus() {
        return resetButton.get();
    }

    private static final DigitalInput stopButton = new DigitalInput(15);
    public static boolean getStopButtonStatus() {
        return stopButton.get();
    }

    private static final AnalogInput sharp = new AnalogInput(0);
    public static double getSharpDistance() {
        return (Math.pow(sharp.getAverageVoltage(), -1.2045) * 27.726);
    }

    private static final Ultrasonic sonar = new Ultrasonic(8, 9);
    public static double getSonarDistance() {
        sonar.ping();
        Timer.delay(0.005);
        return sonar.getRangeMM();
    }

    // private static final AHRS gyro = new AHRS(SPI.Port.kMXP);
    // public double gyroGetAngle() {
    //     return gyro.getAngle();
    // }

    // public void resetGyro() {
    //     gyro.reset();
    // }

}
