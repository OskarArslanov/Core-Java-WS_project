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
    
    private final TitanQuad dcMotor = new TitanQuad(42, 0);
    public void setPWM(double value) {
        dcMotor.set(value);
    }

    private final TitanQuadEncoder enc = new TitanQuadEncoder(dcMotor, 0, 1);
    public double getDC_MotorSpeed() {
        return enc.getSpeed();
    }

    public double getEncoderDistance() {
        return enc.getEncoderDistance();
    }

    public void resetEnc() {
        enc.reset();
    }

    private final DigitalInput emsButton = new DigitalInput(12); 
    public boolean getEMS_ButtonStatus() {
        return emsButton.get();
    }

    private final DigitalInput startButton = new DigitalInput(13);
    public boolean getStartButtonStatus() {
        return startButton.get();
    }

    private final DigitalInput resetButton = new DigitalInput(14);
    public boolean getResetButtonStatus() {
        return resetButton.get();
    }

    private final DigitalInput stopButton = new DigitalInput(15);
    public boolean getStopButtonStatus() {
        return stopButton.get();
    }

    private final AnalogInput sharp = new AnalogInput(0);
    public double getSharpDistance() {
        return (Math.pow(sharp.getAverageVoltage(), -1.2045) * 27.726);
    }

    private final Ultrasonic sonar = new Ultrasonic(8, 9);
    public double getSonarDistance() {
        sonar.ping();
        Timer.delay(0.005);
        return sonar.getRangeMM();
    }

    private final AHRS gyro = new AHRS(SPI.Port.kMXP);
    public double gyroGetAngle() {
        return gyro.getAngle();
    }

    public void resetGyro() {
        gyro.reset();
    }

}
