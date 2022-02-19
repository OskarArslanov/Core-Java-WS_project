package frc.robot.Maths.Odometry;

import frc.robot.Main;

public class PositionController {

    private static final double transformK = 2*50*Math.PI;

    private static double traveledX() {  // reads robot's position X per iteration
        double x = Main.motorControllerMap.get("rpm0") - Main.motorControllerMap.get("rpm1") 
        - Main.motorControllerMap.get("rpm2") + Main.motorControllerMap.get("rpm3");
        return x / transformK / 4; 
    }

    private static double traveledY() {  // reads robot's position Y per iteration
        double y = Main.motorControllerMap.get("rpm0") + Main.motorControllerMap.get("rpm1") 
        + Main.motorControllerMap.get("rpm2") + Main.motorControllerMap.get("rpm3");
        return y / transformK / 4;
    }

    private static double prevZ;
    private static double traveledZ() {
        double result;
        if (Main.sensorsMap.get("resetGyro") == 1) {
            result = 0;
            prevZ = 0;
        } else {
            result = Main.sensorsMap.get("srcGyro") - prevZ;
            prevZ = Main.sensorsMap.get("srcGyro");
        }
        return result;
    }
    private static double newPosX;
    private static double newPosY;
    private static double newPosZ;

    public static void calculateXYZ() {
        double r = Math.sqrt(Math.pow(traveledX(), 2) + Math.pow(traveledY(), 2));

        newPosZ = Main.sensorsMap.get("posZ") + traveledZ();
        Main.sensorsMap.put("posZ", newPosZ);

        double theta = Math.atan2(traveledY(), traveledX()) - Math.toRadians(Main.sensorsMap.get("posZ"));

        double addX = r * Math.cos(theta);
        newPosX = Main.motorControllerMap.get("posX") + addX;
        Main.motorControllerMap.put("posX", newPosX);

        double addY = r * Math.sin(theta);
        newPosY = Main.motorControllerMap.get("posY") + addY;
        Main.motorControllerMap.put("posY", newPosY);       
    }
}
