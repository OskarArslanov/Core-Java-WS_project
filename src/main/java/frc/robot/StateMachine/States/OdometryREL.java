package frc.robot.StateMachine.States;

import frc.robot.Main;
import frc.robot.Maths.Odometry.HolonomicDrive;
import frc.robot.StateMachine.CoreEngine.IState;
import frc.robot.StateMachine.Enums.*;

public class OdometryREL implements IState {

    private double targetX = 0;
    private double targetY = 0;
    private double targetZ = 0;
    private boolean startAcceleration = false;
    private boolean endAcceleration = false;
    private EnumLift enumLift = EnumLift.IDLE;
    private EnumRotation enumRotation = EnumRotation.IDLE;
    private EnumGripper enumGripper = EnumGripper.IDLE;
    private double initX;
    private double initY;
    private double initZ;

    public OdometryREL(double targetX, double targetY, double targetZ, boolean startAcceleration, boolean endAcceleration, EnumLift enumLift, EnumRotation enumRotation, EnumGripper enumGripper) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.startAcceleration = startAcceleration;
        this.endAcceleration = endAcceleration;
        this.enumLift = enumLift;
        this.enumRotation = enumRotation;
        this.enumGripper = enumGripper;
    }

    private void firstIterationXYZ() {
        initX = Main.motorControllerMap.get("posX");
        initY = Main.motorControllerMap.get("posY");
        initZ = Main.sensorsMap.get("posZ");
    }

    private void relativeDirectionPositioning() {
        double angleRelAxis90 = Math.toRadians(targetZ + 90);
        double angleRelAxis0 = Math.toRadians(targetZ);
        double sinRelAxisX = Math.sin(angleRelAxis90) * targetX;
        double sinRelAxisY = Math.sin(angleRelAxis0) * targetY;
        double cosRelAxisX = Math.cos(angleRelAxis90) * targetX;
        double cosRelAxisY = Math.cos(angleRelAxis0) * targetY;
        targetX = sinRelAxisX + sinRelAxisY + initX;
        targetY = cosRelAxisX + cosRelAxisY + initY;
        targetZ = targetZ + initZ;
    }

    @Override
    public void initialize() {
        firstIterationXYZ();
        relativeDirectionPositioning();
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
