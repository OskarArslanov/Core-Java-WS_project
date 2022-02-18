package frc.robot.subsystems;
import com.studica.frc.TitanQuad;
import com.studica.frc.TitanQuadEncoder;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Main;
import frc.robot.Maths.PID;

public class MotorController implements Runnable {

    public static double motorsUpdateTime;
    
    @Override
    public void run() {
        while (true) {
            double startTime = Timer.getFPGATimestamp();
            try {
                Main.motorControllerMap.put("enc0", -ENCODER0.getEncoderDistance());
                Main.motorControllerMap.put("enc1", -ENCODER1.getEncoderDistance());
                Main.motorControllerMap.put("enc2", ENCODER2.getEncoderDistance());
                Main.motorControllerMap.put("enc3", ENCODER3.getEncoderDistance());
                if (Main.motorControllerMap.get("resetEncs") == 1) {
                    ENCODER0.reset();
                    ENCODER1.reset();
                    ENCODER2.reset();
                    ENCODER3.reset();
                    PID0.reset();
                    PID1.reset();
                    PID2.reset();
                    PID3.reset();
                }
                Main.motorControllerMap.put("rpm0", ENCODER0.getSpeed());
                Main.motorControllerMap.put("rpm1", ENCODER1.getSpeed());
                Main.motorControllerMap.put("rpm2", -ENCODER2.getSpeed());
                Main.motorControllerMap.put("rpm3", -ENCODER3.getSpeed());
                Main.motorControllerMap.put("updateTime", motorsUpdateTime);
                setSpeed(Main.motorControllerMap.get("speedX"), 
                Main.motorControllerMap.get("speedY"), Main.motorControllerMap.get("speedZ"));
                Thread.sleep(20);
            } catch (Exception e) {}
            motorsUpdateTime = Timer.getFPGATimestamp() - startTime;
        }
    }
    
    private final TitanQuad MOTOR0 = new TitanQuad(42, 1);
    private final TitanQuad MOTOR1 = new TitanQuad(42, 0);
    private final TitanQuad MOTOR2 = new TitanQuad(42, 3);
    private final TitanQuad MOTOR3 = new TitanQuad(42, 2);

    private final TitanQuadEncoder ENCODER0 = new TitanQuadEncoder(MOTOR0, 1, 1);
    private final TitanQuadEncoder ENCODER1 = new TitanQuadEncoder(MOTOR1, 0, 1);
    private final TitanQuadEncoder ENCODER2 = new TitanQuadEncoder(MOTOR2, 3, 1);
    private final TitanQuadEncoder ENCODER3 = new TitanQuadEncoder(MOTOR3, 2, 1);

    private final PID PID0 = new PID(0.5, 0.1, 0, -100, 100);
    private final PID PID1 = new PID(0.5, 0.1, 0, -100, 100);
    private final PID PID2 = new PID(0.5, 0.1, 0, -100, 100);
    private final PID PID3 = new PID(0.5, 0.1, 0, -100, 100);

    private void setSpeed(double speedX, double speedY, double speedZ) {
        double motorLF = speedY + speedX + speedZ;
        double motorLR = speedY - speedX + speedZ;
        double motorRF = speedY - speedX - speedZ;
        double motorRR = speedY + speedX - speedZ;

        PID0.calculate(ENCODER0.getSpeed(), motorLF);
        PID1.calculate(ENCODER1.getSpeed(), motorLR);
        PID2.calculate(ENCODER2.getSpeed(), motorRF);
        PID3.calculate(ENCODER3.getSpeed(), motorRR);

        // MOTOR0.set(PID0.getOutput());
        // MOTOR1.set(PID1.getOutput());
        // MOTOR2.set(-PID2.getOutput()); // revert
        // MOTOR3.set(-PID3.getOutput()); // revert

        MOTOR0.set(1);
        MOTOR1.set(1);
        MOTOR2.set(-1);
        MOTOR3.set(-1);

    }
}