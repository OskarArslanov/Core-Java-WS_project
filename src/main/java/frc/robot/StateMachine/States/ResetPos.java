package frc.robot.StateMachine.States;

import frc.robot.Main;
import frc.robot.StateMachine.CoreEngine.IState;

public class ResetPos implements IState {

    private double posX, posY, posZ;

    public ResetPos(double posX, double posY, double posZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    @Override
    public void initialize() {
    }

    private boolean readyX;
    private boolean readyY;
    private boolean readyZ;

    @Override
    public void execute() {
        Main.motorControllerMap.put("posX", posX);
        Main.motorControllerMap.put("posY", posY);
        Main.motorControllerMap.put("posZ", posZ);
        readyX = posX == Main.motorControllerMap.get("posX");
        readyY = posY == Main.motorControllerMap.get("posY");
        if (this.posZ == -1) {
            readyZ = true;
        } else {
            readyZ = posZ == Main.motorControllerMap.get("posZ");
            Main.sensorsMap.put("resetGyro", 1.0);
        }
    }

    @Override
    public void finilize() {
    }

    @Override
    public boolean isFinished() {
        return readyX && readyY && readyZ;
    }  
}