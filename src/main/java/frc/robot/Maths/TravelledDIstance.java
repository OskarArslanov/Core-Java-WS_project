package frc.robot.Maths;

import frc.robot.Main;

public abstract class TravelledDIstance {
    private static final double transformK = 2*50*Math.PI;

    public double traveledX() {  // reads robot's position X per iteration
        double x = Main.motorControllerMap.get("rpm0") - Main.motorControllerMap.get("rpm1") 
        - Main.motorControllerMap.get("rpm2") + Main.motorControllerMap.get("rpm3");
        return x / transformK / 4; 
    }

    public double traveledY() {  // reads robot's position Y per iteration
        double y = Main.motorControllerMap.get("rpm0") + Main.motorControllerMap.get("rpm1") 
        + Main.motorControllerMap.get("rpm2") + Main.motorControllerMap.get("rpm3");
        return y / transformK / 4;
    }
}
