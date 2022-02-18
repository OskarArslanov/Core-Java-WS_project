package frc.robot.Maths.Sensors;

import frc.robot.Main;
import frc.robot.Maths.Common.Limits;
import frc.robot.Maths.Common.TF;
import frc.robot.StateMachine.CoreEngine.StateMachine;

public class SensorsSimple {

    private double deltaX, deltaY, deltaZ;
    private boolean stopX, stopY, stopZ;
    
    public void correct(double sideWall, double straightWall, boolean startAcceleration, boolean endAcceleration) {
        double startAccelerationK = 1;
        if (startAcceleration) {
            TF.TIMER_TO_K.calculate(StateMachine.iterationTime);
            startAccelerationK = TF.TIMER_TO_K.getOutput();
        }
        
        TF endAccelerationSharpK;
        TF endAccelerationSonarK;
        if (endAcceleration) {
            endAccelerationSonarK = TF.SONAR_END;
            endAccelerationSharpK = TF.SHARP_END;
        } else {
            endAccelerationSonarK = TF.SONAR_CONT;
            endAccelerationSharpK = TF.SHARP_CONT;
        }
        calcDeltaX(sideWall, endAcceleration);
        calcDeltaY(straightWall, endAcceleration);
        calcDeltaZ(straightWall, endAcceleration);

        endAccelerationSonarK.calculate(deltaX);
        endAccelerationSharpK.calculate(deltaY);
        TF.SENSORS_ANGLE_TF.calculate(deltaZ);

        Main.motorControllerMap.put("speedX", endAccelerationSonarK.getOutput() * startAccelerationK);
        Main.motorControllerMap.put("speedY", endAccelerationSharpK.getOutput() * startAccelerationK);
        Main.motorControllerMap.put("speedZ", TF.SENSORS_ANGLE_TF.getOutput() * startAccelerationK);
    }

    public boolean isFinished() {
        return stopX && stopY && stopZ;
    }

    private void calcDeltaX(double sideWall, boolean endAcceleration) {
        if (sideWall != 0) {
            if (sideWall > 0) {
                deltaX = Main.sensorsMap.get("sonarRight") - sideWall;
            } else {
                deltaX = Math.abs(sideWall) - Main.sensorsMap.get("sonarLeft");
            } 

            double error;
            if (endAcceleration) {
                error = 0.1;
                stopX = Limits.isValueInLimits(deltaX, -error, error);
            } else {
                error = 5;
                stopX = Limits.isValueInLimits(deltaX, -error, error);
            }
    
        } else {
            stopX = true;
        }
    }

    private void calcDeltaY(double straightWall, boolean endAcceleration) {
        double activeSharp;
        if (Main.sensorsMap.get("sharpLeft") > Main.sensorsMap.get("sharpRight")) {
            activeSharp = Main.sensorsMap.get("sharpLeft");
        } else {
            activeSharp = Main.sensorsMap.get("sharpRight");
        }
        deltaY = activeSharp - straightWall;
        if (straightWall != 0) {
            double error;
            if (endAcceleration) {
                error = 0.1;
                stopY = Limits.isValueInLimits(deltaY, -error, error);
            } else {
                error = 5;
                stopY = Limits.isValueInLimits(deltaY, -error, error);
            }
        } else {
            stopY = true;
        }
    }

    private void calcDeltaZ(double straightWall, boolean endAcceleration) {
        double averageSharpDistance = (Main.sensorsMap.get("sharpLeft") + Main.sensorsMap.get("sharpRight")) / 2;
        if (averageSharpDistance > straightWall) {
            stopZ = true;
            deltaZ = -Main.sensorsMap.get("gyro");
        } else {
            deltaZ = Main.sensorsMap.get("sharpRight") - Main.sensorsMap.get("sharpLeft");
            double error;
            if (endAcceleration) {
                error = 0.1;
                stopZ = Limits.isValueInLimits(deltaZ, -error, error);
            } else {
                error = 5;
                stopZ = Limits.isValueInLimits(deltaZ, -error, error);
            }
        }
    }
}
