package frc.robot.Maths.Sensors;

import frc.robot.Main;
import frc.robot.Maths.Common.TF;
import frc.robot.StateMachine.CoreEngine.StateMachine;

public class SonarSearchingWall {
    
    private boolean stop;
    
    public void searchWall(double wallDistance, double speed, boolean startAcceleration) {
        double startAccelerationK = 1;
        if (startAcceleration) {
            TF.TIMER_TO_K.calculate(StateMachine.iterationTime);
            startAccelerationK = TF.TIMER_TO_K.getOutput();
        }
        Main.motorControllerMap.put("speedX", 0.0);
        Main.motorControllerMap.put("speedY", speed*startAccelerationK);
        Main.motorControllerMap.put("speedZ", 0.0);
        checkWall(wallDistance);
    }

    public boolean isFinish() {
        return stop;
    }

    private void checkWall(double wallDistance) {
        if (wallDistance < 0) {
            stop = (Math.abs(wallDistance) + 5) > Main.sensorsMap.get("sonarLeft"); // plus lose error
        } else {
            stop = Main.sensorsMap.get("sonarRight") < (wallDistance + 5); // plus lose error
        }
    }
}
