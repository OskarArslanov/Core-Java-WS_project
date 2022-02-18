package frc.robot.Maths.Odometry;

import frc.robot.Main;
import frc.robot.Maths.Common.Limits;
import frc.robot.Maths.Common.TF;
import frc.robot.StateMachine.CoreEngine.StateMachine;

public class HolonomicDrive extends PositionController {
    private static boolean[] finish = new boolean[2];

    public static void move(double distanceX, double distanceY, double distanceZ, boolean isStartAcceleration, boolean isEndAcceleration) {
        double way = Math.hypot(distanceX - Main.motorControllerMap.get("posX"), 
        distanceY - Main.motorControllerMap.get("posY"));
        double angle = distanceZ - Main.sensorsMap.get("posZ");
        double holonomicRotation = Math.atan2(distanceY - Main.motorControllerMap.get("posY"),
         distanceX - Main.motorControllerMap.get("posX")) + Math.toRadians(Main.sensorsMap.get("posZ")) ;
        calcSpeeds(way, angle, holonomicRotation, isStartAcceleration, isEndAcceleration);
        calcFinish(way, angle, isEndAcceleration);
    }

    private static void calcSpeeds(double way, double angle, double holonomicRotation, boolean isStartAcceleration, boolean isEndAcceleration) {
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
    
    private static void calcFinish(double way, double angle, boolean isEndAcceleration) {
        if (isEndAcceleration) {
            finish[0] = way < 0.5;
            finish[1] = Limits.isValueInLimits(angle, -0.25, 0.25);
        } else {
            finish[0] = way < 10;
            finish[1] = Limits.isValueInLimits(angle, -5, 5);
        }
    }

    public static boolean isFinish() {
        return finish[0] && finish[1];
    }
}
