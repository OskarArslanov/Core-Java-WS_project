package frc.robot.subsystems;
import com.studica.frc.TitanQuad;
import com.studica.frc.TitanQuadEncoder;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Main;

public class MotorController implements Runnable {

    public static double motorsUpdateTime;
    
    @Override
    public void run() {
        while (true) {
            double startTime = Timer.getFPGATimestamp();
            try {
                Main.motorControllerMap.put("encDistance0", ENCODER0.getEncoderDistance());
                Main.motorControllerMap.put("encDistance1", ENCODER1.getEncoderDistance());
                Main.motorControllerMap.put("encDistance2", ENCODER2.getEncoderDistance());
                Main.motorControllerMap.put("encDistance3", ENCODER3.getEncoderDistance());
                if (Main.motorControllerMap.get("resetEncs") == 1) {
                    ENCODER0.reset();
                    ENCODER1.reset();
                    ENCODER2.reset();
                    ENCODER3.reset();
                }
                Main.motorControllerMap.put("getMotorSpeed0", ENCODER0.getSpeed());
                Main.motorControllerMap.put("getMotorSpeed1", ENCODER1.getSpeed());
                Main.motorControllerMap.put("getMotorSpeed2", ENCODER2.getSpeed());
                Main.motorControllerMap.put("getMotorSpeed3", ENCODER3.getSpeed());
                Main.motorControllerMap.put("updateTime", motorsUpdateTime);
                MOTOR0.set(Main.motorControllerMap.get("setMotorSpeed0"));
                MOTOR1.set(Main.motorControllerMap.get("setMotorSpeed1"));
                MOTOR2.set(Main.motorControllerMap.get("setMotorSpeed2"));
                MOTOR3.set(Main.motorControllerMap.get("setMotorSpeed3"));
                Thread.sleep(20);
            } catch (Exception e) {}
            motorsUpdateTime = Timer.getFPGATimestamp() - startTime;
        }
    }
    
    private static final TitanQuad MOTOR0 = new TitanQuad(42, 0);
    private static final TitanQuad MOTOR1 = new TitanQuad(42, 1);
    private static final TitanQuad MOTOR2 = new TitanQuad(42, 2);
    private static final TitanQuad MOTOR3 = new TitanQuad(42, 3);

    private static final TitanQuadEncoder ENCODER0 = new TitanQuadEncoder(MOTOR0, 0, 1);
    private static final TitanQuadEncoder ENCODER1 = new TitanQuadEncoder(MOTOR1, 1, 1);
    private static final TitanQuadEncoder ENCODER2 = new TitanQuadEncoder(MOTOR2, 2, 1);
    private static final TitanQuadEncoder ENCODER3 = new TitanQuadEncoder(MOTOR3, 3, 1);
}