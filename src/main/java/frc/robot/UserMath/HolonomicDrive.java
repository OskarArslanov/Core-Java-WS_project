package frc.robot.UserMath;

import frc.robot.Actions;

public class HolonomicDrive extends OdometryRead {
        public static double[] speeds = new double[3];
        private static boolean[] finish = new boolean[2];

        private void calcSpeeds(double way, double angle, double holonomicRotation, boolean isStartAcceleration, boolean isEndAcceleration) {
            double kAccelarion;
            if (isStartAcceleration) {
                kAccelarion = TransferFunction.TIMER_TO_K.getOutValue(Actions.iterationTime(false));
            } else {
                kAccelarion = 1;
            }

            TransferFunction DISTANCE_LAST;
            if (isEndAcceleration) {
                DISTANCE_LAST = TransferFunction.DISTANCE_TF_END;
            } else {
                DISTANCE_LAST = TransferFunction.DISTANCE_TF_CONTINUOUS;
            }
        
            double accumulatedDistanceSpeed = DISTANCE_LAST.getOutValue(way) * kAccelarion; // convert distance to speed
            double accumulatedDistanceAngle = TransferFunction.GYROSCOPE_ANGLE_TF.getOutValue(angle) * kAccelarion; // convert angle distance to speed
            
            speeds[0] = accumulatedDistanceSpeed * Math.cos(holonomicRotation);
            speeds[1] = accumulatedDistanceSpeed * Math.sin(holonomicRotation);
            speeds[2] = accumulatedDistanceAngle;
            setSpeed(speeds[0], speeds[1], speeds[2]);
        }
    
        public void distanceToSpeed(double distanceX, double distanceY, double distanceZ, boolean isStartAcceleration, boolean isEndAcceleration) {
            double way = Math.hypot(distanceX - getPositionX(), distanceY - getPositionY());
            double angle = distanceZ - getPositionZ();
            double holonomicRotation = Math.atan2(distanceY - getPositionY(), distanceX - getPositionX()) + Math.toRadians(getPositionZ()) ;
            calcSpeeds(way, angle, holonomicRotation, isStartAcceleration, isEndAcceleration);
            calcFinish(way, angle, isEndAcceleration);
        }
    
        private void calcFinish(double way, double angle, boolean isEndAcceleration) {
            if (isEndAcceleration) {
                finish[0] = way < 0.5;
                finish[1] = Limits.inLimit(angle, -0.25, 0.25);
            } else {
                finish[0] = way < 10;
                finish[1] = Limits.inLimit(angle, -5, 5);
            }
        }

        public boolean isFinish() {
            return finish[0] && finish[1];
        }

}
