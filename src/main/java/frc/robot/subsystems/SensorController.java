package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.Main;

public class SensorController implements Runnable{

    public static double sensorsUpdateTime;

    @Override
    public void run() {
        while (true) {
            double startTime = Timer.getFPGATimestamp();
            try {
                Main.sensorsMap.put("gyro", GYRO.getAngle());
                if (Main.sensorsMap.get("resetGyro") == 1) {
                    GYRO.reset();
                }
                Main.sensorsMap.put("sharpLeft", SHARP_LEFT.getAverageVoltage());
                Main.sensorsMap.put("sharpRight", SHARP_RIGHT.getAverageVoltage());
                Main.sensorsMap.put("sonarLeft", getLeftSonar());
                Main.sensorsMap.put("sonarRight", getRightSonar());
                Main.switchMap.put("startButtonStatus", START_BUTTON.get());
                Main.switchMap.put("resetButtonStatus", RESET_BUTTON.get());
                Main.switchMap.put("stopButtonStatus", STOP_BUTTON.get());
                Main.switchMap.put("EMSButtonStatus", EMS_BUTTON.get());
                Main.switchMap.put("LS_Top", LM_SWITCH_TOP.get());
                Main.switchMap.put("LS_Bottom", LM_SWITCH_BOTTOM.get());
                Main.sensorsMap.put("updateTime", sensorsUpdateTime);
                Thread.sleep(20);
            } catch (Exception e) {}
            sensorsUpdateTime = Timer.getFPGATimestamp() - startTime;
        }
    }
    
    private static final AHRS GYRO = new AHRS(SPI.Port.kMXP);
    private static final AnalogInput SHARP_LEFT = new AnalogInput(0);
    private static final AnalogInput SHARP_RIGHT = new AnalogInput(1);
    private static final Ultrasonic SONAR_LEFT = new Ultrasonic(8, 9);
    private static final Ultrasonic SONAR_RIGHT = new Ultrasonic(2, 3);
    private static final DigitalInput START_BUTTON = new DigitalInput(13);
    private static final DigitalInput RESET_BUTTON = new DigitalInput(14);
    private static final DigitalInput STOP_BUTTON = new DigitalInput(15);
    private static final DigitalInput EMS_BUTTON = new DigitalInput(12);
    private static final DigitalInput LM_SWITCH_TOP = new DigitalInput(16);
    private static final DigitalInput LM_SWITCH_BOTTOM = new DigitalInput(17);

    private double getLeftSonar() {
        SONAR_LEFT.ping();
        Timer.delay(0.005);
        return SONAR_LEFT.getRangeMM();
    }

    private double getRightSonar() {
        SONAR_RIGHT.ping();
        Timer.delay(0.005);
        return SONAR_RIGHT.getRangeMM();
    }
}
