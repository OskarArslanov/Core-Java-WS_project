package frc.robot.StateMachine.States;

import frc.robot.Main;
import frc.robot.Maths.Odometry.HolonomicDrive;
import frc.robot.StateMachine.CoreEngine.IState;
import frc.robot.StateMachine.Enums.*;

public class OdometryABS implements IState {

    private double targetX = 0;
    private double targetY = 0;
    private double targetZ = 0;
    private boolean startAcceleration = false;
    private boolean endAcceleration = false;
    private EnumLift enumLift = EnumLift.IDLE;
    private EnumRotation enumRotation = EnumRotation.IDLE;
    private EnumGripper enumGripper = EnumGripper.IDLE;


    public OdometryABS(double targetX, double targetY, double targetZ, boolean startAcceleration, boolean endAcceleration, EnumLift enumLift, EnumRotation enumRotation, EnumGripper enumGripper) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.startAcceleration = startAcceleration;
        this.endAcceleration = endAcceleration;
        this.enumLift = enumLift;
        this.enumRotation = enumRotation;
        this.enumGripper = enumGripper;
    }

    public void disableXYZ() {
        if (targetX == -1) {
            targetX = Main.motorControllerMap.get("posX");
        }
        if (targetY == -1) {
            targetY = Main.motorControllerMap.get("posY");
        }
        if (targetZ == -1) {
            targetZ = Main.sensorsMap.get("posZ");
        }
    }

    @Override
    public void initialize() {
        disableXYZ();
    }

    @Override
    public void execute() {
        HolonomicDrive.move(targetX, targetY, targetZ, startAcceleration, endAcceleration);
    }

    @Override
    public void finilize() {
        if (this.endAcceleration) {
            Main.motorControllerMap.put("speedX", 0.0);
            Main.motorControllerMap.put("speedY", 0.0);
            Main.motorControllerMap.put("speedZ", 0.0);
        }
    }

    @Override
    public boolean isFinished() {
        return HolonomicDrive.isFinish();
    }

}
