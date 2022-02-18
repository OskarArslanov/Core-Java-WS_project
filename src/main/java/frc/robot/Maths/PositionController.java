package frc.robot.Maths;

import frc.robot.Main;

public class PositionController extends TravelledDIstance {
    
    private static double positionX;
    private static double positionY;
    private static double positionZ;

    public double getPositionX() {
        double r = Math.sqrt(Math.pow(traveledX(), 2) + Math.pow(traveledY(), 2));
        double theta = Math.atan2(traveledY(), traveledX()) - Math.toRadians(getPositionZ());
        double addX = r * Math.cos(theta);
        positionX = positionX + addX;
        return positionX;
    }

    public double getPositionY() {
        double r = Math.sqrt(Math.pow(traveledX(), 2) + Math.pow(traveledY(), 2));
        double theta = Math.atan2(traveledY(), traveledX()) - Math.toRadians(getPositionZ());
        double addY = r * Math.sin(theta);
        positionY = positionY + addY;
        return positionY;
    }

    public double getPositionZ() {
        positionZ = Main.sensorsMap.get("gyro");
        return positionZ;
    }

    public void setPosition(double posX, double posY, double posZ) {
        positionX = posX;
        positionY = posY;
        positionZ = posZ;
    }
}
