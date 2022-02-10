package frc.robot.subsystems;

import com.studica.frc.TitanQuad;
import com.studica.frc.TitanQuadEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.UserMath.Limits;

public class DC_Motors {
    private static final double transformK = 2*50*Math.PI;  //  Радиус колеса

    private static final TitanQuad motorBack = new TitanQuad(42, 0);
    private static final TitanQuad motorLeft = new TitanQuad(42, 1);
    // private static final TitanQuad motorNull = new TitanQuad(42, 2);
    private static final TitanQuad motorRight = new TitanQuad(42, 3);

    private static final TitanQuadEncoder encBack = new TitanQuadEncoder(motorBack, 0, 1);
    private static final TitanQuadEncoder encLeft = new TitanQuadEncoder(motorLeft, 1, 1);
    // private static final TitanQuadEncoder encNull = new TitanQuadEncoder(motorNull, 2, 1);
    private static final TitanQuadEncoder encRight = new TitanQuadEncoder(motorRight, 3, 1);

    public double getEncDistance(int input) {
        double value = 0;
        switch (input){
            case 0 : value = encLeft.getEncoderDistance() / -transformK;
            break;
            case 1 : value = encBack.getEncoderDistance() / transformK;
            break;
            case 2 : value = encRight.getEncoderDistance() / transformK;
            break;
            case 3 :  
            value = 0;
            // value = encNull.getEncoderDistance() / -transformK;
            break;
        }
        return value;
    }

    public double getEncSpeed(int input) {
        double value = 0;
        switch (input){
            case 0 : value = encLeft.getSpeed() / 1;
            break;
            case 1 : value = encBack.getSpeed() / -1;
            break;
            case 2 : value = encRight.getSpeed() / -1;
            break;
            case 3 : 
            value = 0;
            // value = encNull.getSpeed() / 1;
            break;
        }
        return Limits.limitOf(value, -100, 100);
    }

    public void setMotorSpeed(int input, double value) {
        switch (input){
            case 0 : motorLeft.set(value * 1);
            break;
            case 1 : motorBack.set(value * -1);
            break;
            case 2 : motorRight.set(value * -1);
            break;
            case 3 :
            value = 0;
            // motorNull.set(value);
            break;
        }
    }
}
