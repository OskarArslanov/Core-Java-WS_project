package frc.robot.Maths;

import frc.robot.Main;
import frc.robot.StateMachine.CoreEngine.StateMachine;

public class HolonomicDrive extends PositionController {
    private static boolean[] finish = new boolean[2];

    private void calcSpeeds(double way, double angle, double holonomicRotation, boolean isStartAcceleration, boolean isEndAcceleration) {
        double kAccelarion;
        if (isStartAcceleration) {
            TF.TIMER_TO_K.calculate(StateMachine.iterationTime);
            kAccelarion = TF.TIMER_TO_K.getOutput();
        } else {
            kAccelarion = 1;
        }

        TF DISTANCE_LAST;
        if (isEndAcceleration) {
            DISTANCE_LAST = TF.DISTANCE_TF_END;
        } else {
            DISTANCE_LAST = TF.DISTANCE_TF_CONTINUOUS;
        }
        
        DISTANCE_LAST.calculate(way);
        double accumulatedDistanceSpeed = DISTANCE_LAST.getOutput() * kAccelarion; // convert distance to speed

        TF.GYROSCOPE_ANGLE_TF.calculate(angle);
        double accumulatedDistanceAngle = TF.GYROSCOPE_ANGLE_TF.getOutput() * kAccelarion; // convert angle distance to speed

        Main.motorControllerMap.put("speedX", accumulatedDistanceSpeed * Math.cos(holonomicRotation));
        Main.motorControllerMap.put("speedY", accumulatedDistanceSpeed * Math.sin(holonomicRotation));
        Main.motorControllerMap.put("speedZ", accumulatedDistanceAngle);
    }
    
    public void move(double distanceX, double distanceY, double distanceZ, boolean isStartAcceleration, boolean isEndAcceleration) {
        double way = Math.hypot(distanceX - getPositionX(), distanceY - getPositionY());
        double angle = distanceZ - getPositionZ();
        double holonomicRotation = Math.atan2(distanceY - getPositionY(), distanceX - getPositionX()) + Math.toRadians(getPositionZ()) ;
        calcSpeeds(way, angle, holonomicRotation, isStartAcceleration, isEndAcceleration);
        calcFinish(way, angle, isEndAcceleration);
    }
    
    private void calcFinish(double way, double angle, boolean isEndAcceleration) {
        if (isEndAcceleration) {
            finish[0] = way < 0.5;
            finish[1] = Limits.isValueInLimits(angle, -0.25, 0.25);
        } else {
            finish[0] = way < 10;
            finish[1] = Limits.isValueInLimits(angle, -5, 5);
        }
    }

    public boolean isFinish() {
        return finish[0] && finish[1];
    }
}
