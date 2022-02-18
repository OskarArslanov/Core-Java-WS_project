package frc.robot.Maths.Sensors;

import frc.robot.Main;
import frc.robot.Maths.Common.TF;
import frc.robot.StateMachine.CoreEngine.StateMachine;

public class SharpLoosingWall {

    private boolean stop;
    private double speedY;

    public void looseWall(double wallDistance, double speed, boolean startAcceleration) {
        double startAccelerationK = 1;
        if (startAcceleration) {
            TF.TIMER_TO_K.calculate(StateMachine.iterationTime);
            startAccelerationK = TF.TIMER_TO_K.getOutput();
        }
        calcSpeedY(wallDistance);
        Main.motorControllerMap.put("speedX", speed*startAccelerationK);
        Main.motorControllerMap.put("speedY", getSpeedY()*startAccelerationK);
        Main.motorControllerMap.put("speedZ", 0.0);
        checkWall(wallDistance);
    }

    public boolean isFinish() {
        return stop;
    }

    private void calcSpeedY(double wallDistance) {
        if (wallDistance < 0) {
            speedY = Main.sensorsMap.get("sharpLeft") - Math.abs(wallDistance);
        } else {
            speedY = Main.sensorsMap.get("sharpRight") - Math.abs(wallDistance);
        }
    }

    private double getSpeedY() {
        return speedY;
    }

    private void checkWall(double wallDistance) {
        if (wallDistance < 0) {
            stop = Main.sensorsMap.get("sharpLeft") > (Math.abs(wallDistance) + 5); // plus lose error
        } else {
            stop = Main.sensorsMap.get("sharpRight") > (Math.abs(wallDistance) + 5); // plus lose error
        }
    }
}
