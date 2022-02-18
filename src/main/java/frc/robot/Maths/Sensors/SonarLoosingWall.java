package frc.robot.Maths.Sensors;

import frc.robot.Main;
import frc.robot.Maths.Common.TF;
import frc.robot.StateMachine.CoreEngine.StateMachine;

public class SonarLoosingWall {

    private boolean stop;
    private double speedX;

    public void looseWall(double wallDistance, double speed, boolean startAcceleration) {
        double startAccelerationK = 1;
        if (startAcceleration) {
            TF.TIMER_TO_K.calculate(StateMachine.iterationTime);
            startAccelerationK = TF.TIMER_TO_K.getOutput();
        }
        calcSpeedX(wallDistance);
        Main.motorControllerMap.put("speedX", getSpeedX()*startAccelerationK);
        Main.motorControllerMap.put("speedY", speed*startAccelerationK);
        Main.motorControllerMap.put("speedZ", 0.0);
        checkWall(wallDistance);
    }

    public boolean isFinish() {
        return stop;
    }

    private void calcSpeedX(double wallDistance) {
        if (wallDistance < 0) {
            speedX = Math.abs(wallDistance) - Main.sensorsMap.get("sonarLeft");
        } else {
            speedX = Main.sensorsMap.get("sonarRight") - wallDistance;
        }
    }

    private double getSpeedX() {
        return speedX;
    }

    private void checkWall(double wallDistance) {
        if (wallDistance < 0) {
            stop = (Math.abs(wallDistance) + 5) < Main.sensorsMap.get("sonarLeft"); // plus lose error
        } else {
            stop = Main.sensorsMap.get("sonarRight") > (wallDistance + 5); // plus lose error
        }
    }    
}

