package frc.robot.StateMachine.States;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.StateMachine.Enums.*;

public class OMS extends CommandBase {
    private EnumLift lift;
    private EnumRotation rotation;
    private EnumGripper gripper;
    
    public OMS(EnumLift lift, EnumRotation rotation, EnumGripper gripper){
        
        this.rotation = rotation;
        this.lift = lift;
        this.gripper = gripper;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    }

    @Override
    public void end (boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }    
}
 