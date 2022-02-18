package frc.robot.Maths.Odometry;

import frc.robot.Main;

public class PositionController {
    
    private static double positionX;
    private static double positionY;

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

    public static void calculate() {
        double r = Math.sqrt(Math.pow(traveledX(), 2) + Math.pow(traveledY(), 2));
        double theta = Math.atan2(traveledY(), traveledX()) - Math.toRadians(Main.sensorsMap.get("posZ"));

        double addX = r * Math.cos(theta);
        positionX = positionX + addX;
        Main.motorControllerMap.put("posX", positionX);

        double addY = r * Math.sin(theta);
        positionY = positionY + addY;
        Main.motorControllerMap.put("posY", positionY);
    }
}
