package frc.robot.UserMath;

import frc.robot.subsystems.MobilityControl;

public abstract class TraveledDistance extends MobilityControl {
    
    private static final double transformK = 2*50*Math.PI;

    public double traveledX() {  // reads robot's position X per iteration
        double x = ((dc_Motors.getEncSpeed(0) - dc_Motors.getEncSpeed(2)) + dc_Motors.getEncSpeed(1))/ 2 / transformK;
        return x * 0.66666666666666667; 
    }

    public double traveledY() {  // reads robot's position Y per iteration
        double y = (dc_Motors.getEncSpeed(0) + dc_Motors.getEncSpeed(2)) / 2 / transformK;
        return y;
    }
}
