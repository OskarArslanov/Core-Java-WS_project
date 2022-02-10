package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class Gyroscope {
    private static final AHRS gyro = new AHRS(SPI.Port.kMXP);

    public double gyroGetAngle() {
        return gyro.getAngle();
    }

    public void resetGyro() {
        gyro.reset();
    }
    
}
