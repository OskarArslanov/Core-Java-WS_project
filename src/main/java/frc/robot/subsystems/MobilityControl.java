package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.UserMath.PID_Regulator;


public class MobilityControl implements Runnable {
    
    public static double upDateTime;

    public void run() {
        while(true) {
            double prevTime = Timer.getFPGATimestamp();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            upDateTime = Timer.getFPGATimestamp() - prevTime;
        }
    }

    public DC_Motors dc_Motors = new DC_Motors();
    public Gyroscope gyroscope = new Gyroscope();
    public PID_Regulator pid_Regulator0 = new PID_Regulator(0.75, 0.2, 0, -100, 100);
    public PID_Regulator pid_Regulator1 = new PID_Regulator(0.75, 0.2, 0, -100, 100);
    public PID_Regulator pid_Regulator2 = new PID_Regulator(0.75, 0.2, 0, -100, 100);
    public PID_Regulator pid_Regulator3 = new PID_Regulator(0.5, 0.05, 0, -100, 100);

    public void setSpeed(double speedX, double speedY, double speedZ) {
        double motorL = speedY + speedX/2 + speedZ;
        double motorB = 0 + speedX - speedZ;
        double motorR = speedY - speedX/2 - speedZ;

        double pwm0 = pid_Regulator0.calculate(dc_Motors.getEncSpeed(0), motorL) / 100;
        double pwm1 = pid_Regulator1.calculate(dc_Motors.getEncSpeed(1), motorB) / 100;
        double pwm2 = pid_Regulator2.calculate(dc_Motors.getEncSpeed(2), motorR) / 100;

        dc_Motors.setMotorSpeed(0, pwm0);
        dc_Motors.setMotorSpeed(1, pwm1);
        dc_Motors.setMotorSpeed(2, pwm2);
    }
}
