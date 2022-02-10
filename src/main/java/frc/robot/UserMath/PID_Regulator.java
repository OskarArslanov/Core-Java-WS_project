package frc.robot.UserMath;

public class PID_Regulator {
    private boolean isFirstCall = true;
    private double kP, kI, kD, lowerLimit, upperLimit;
    private double errorP, errorI, errorD;
    private double output;

    public PID_Regulator (double kP, double kI, double kD, double lowerLimit, double upperLimit) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public double calculate(double input, double setPoint) {
        if (isFirstCall) {
            errorP = 0;
            errorI = 0;
            errorD = 0;
            isFirstCall = false;
        } else {
            errorP = (setPoint - input)*kP;
            errorI += errorP*kI;
            errorD += -errorI*kD;
        }
        output = Limits.limitOf((errorP + errorI + errorD), lowerLimit, upperLimit);
        return output;
    }

    public void reset() {
        this.isFirstCall = true;
    }

    public double getOutput() {
        return output;
    }
}
