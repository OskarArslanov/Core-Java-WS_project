package frc.robot.subsystems;
import com.studica.frc.TitanQuad;
import com.studica.frc.TitanQuadEncoder;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Main;
import frc.robot.Maths.Common.PID;

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
                    Main.motorControllerMap.put("resetEncs", 0.0);
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
    
    private final TitanQuad MOTOR0 = new TitanQuad(42, 2);
    private final TitanQuad MOTOR1 = new TitanQuad(42, 3);
    private final TitanQuad MOTOR2 = new TitanQuad(42, 0);
    private final TitanQuad MOTOR3 = new TitanQuad(42, 1);

    private final TitanQuadEncoder ENCODER0 = new TitanQuadEncoder(MOTOR0, 2, 1);
    private final TitanQuadEncoder ENCODER1 = new TitanQuadEncoder(MOTOR1, 3, 1);
    private final TitanQuadEncoder ENCODER2 = new TitanQuadEncoder(MOTOR2, 0, 1);
    private final TitanQuadEncoder ENCODER3 = new TitanQuadEncoder(MOTOR3, 1, 1);

    private final PID PID0 = new PID(0.5, 0.025, 0.0001, -100, 100);
    private final PID PID1 = new PID(0.5, 0.025, 0.0001, -100, 100);
    private final PID PID2 = new PID(0.5, 0.025, 0.0001, -100, 100);
    private final PID PID3 = new PID(0.5, 0.025, 0.0001, -100, 100);

    private void setSpeed(double speedX, double speedY, double speedZ) {
        double motorLF = speedY + speedX + speedZ;
        double motorLR = speedY - speedX + speedZ;
        double motorRF = speedY - speedX - speedZ;
        double motorRR = speedY + speedX - speedZ;

        PID0.calculate(ENCODER0.getSpeed(), motorLF);
        PID1.calculate(ENCODER1.getSpeed(), motorLR);
        PID2.calculate(-ENCODER2.getSpeed(), motorRF);
        PID3.calculate(-ENCODER3.getSpeed(), motorRR);

        Main.motorControllerMap.put("PID0", PID0.getOutput());
        Main.motorControllerMap.put("PID1", PID1.getOutput());
        Main.motorControllerMap.put("PID2", PID2.getOutput());
        Main.motorControllerMap.put("PID3", PID3.getOutput());

        MOTOR0.set(Main.motorControllerMap.get("PID0"));
        MOTOR1.set(Main.motorControllerMap.get("PID1"));
        MOTOR2.set(-Main.motorControllerMap.get("PID2")); // revert
        MOTOR3.set(-Main.motorControllerMap.get("PID3")); // revert

    }
}