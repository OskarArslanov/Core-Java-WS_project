package frc.robot.UserMath;

public class TransferFunction {
    final private double[] inArray;
    final private double[] outArray;

    public TransferFunction(double[] inArray, double[] outArray){
        this.inArray = inArray;
        this.outArray = outArray;
    }

    public double getOutValue(double inputValue) {
        boolean isOutOfMax =  inputValue > inArray[inArray.length-1];
        boolean isOutOfMin = inputValue < inArray[0];
        boolean isInRange = (!isOutOfMax && !isOutOfMin);
        double transferredValue = endValueOut(isOutOfMax);
        if (isInRange) {
            int counter = 0;
            while (true) {
                if (isInRangeBetween2Values(inputValue, inArray, counter)) {
                    transferredValue = endValueIn(inputValue, counter);
                    break;
                }
                counter++;
            }
        }
        return transferredValue;
    }

    private double endValueIn(double inputValue, int counter) {
        double deltaStartValues = inArray[counter + 1] - inArray[counter];
        double deltaInputStartValues = inputValue - inArray[counter];
        double kStartValues = deltaInputStartValues / deltaStartValues;
        double deltaEndValues = outArray[counter + 1] - outArray[counter];
        double multiplyK = kStartValues*deltaEndValues;
        return multiplyK+outArray[counter];
    }

    private double endValueOut(boolean isOutOfMax) {
        if (isOutOfMax) {
            return outArray[outArray.length-1];
        } else {
            return outArray[0];
        }
    }

    private boolean isInRangeBetween2Values(double inputValue, double[] inArray, int counter ) {
        boolean isInputLessOrEqualNextValue = inputValue <= inArray[counter+1];
        boolean isInputGreaterOrEqualPrevValue = inputValue >= inArray[counter];
        return isInputGreaterOrEqualPrevValue && isInputLessOrEqualNextValue;
    }

    final static private double[] ANGLE_DELTA = { -10, -3, 3, 10 };
    final static private double[] ANGLE_SPEED = { -30, -10, 10, 30 };
    final static public TransferFunction GYROSCOPE_ANGLE_TF = new TransferFunction(ANGLE_DELTA, ANGLE_SPEED);

    final static private double[] DISTANCE_DELTA_END = { 0, 3, 5, 15, 20 };
    final static private double[] DISTANCE_SPEED_END = { 0, 7, 15, 40, 70 };
    final static public TransferFunction DISTANCE_TF_END = new TransferFunction(DISTANCE_DELTA_END, DISTANCE_SPEED_END);

    final static private double[] DISTANCE_DELTA_CONTINUOUS = { 0, 50, 150 };
    final static private double[] DISTANCE_SPEED_CONTINUOUS = { 35, 50, 70 };
    final static public TransferFunction DISTANCE_TF_CONTINUOUS = new TransferFunction(DISTANCE_DELTA_CONTINUOUS, DISTANCE_SPEED_CONTINUOUS);

    final static private double[] TIMER_IN = { 0, 0.05, 0.6, 1 };
    final static private double[] K_OUT = { 0, 0.1, 0.4, 1 };
    final static public TransferFunction TIMER_TO_K = new TransferFunction(TIMER_IN, K_OUT);

    final static private double[] CORRECT_Z_POSITION_DELTA = {-10, -3, -1, 1, 3, 10};
    final static private double[] CORRECT_Z_POSITION_SPEED = {-15, -10, -3, 3, 10, 15};
    final static public TransferFunction SENSORS_ANGLE_TF = new TransferFunction(CORRECT_Z_POSITION_DELTA, CORRECT_Z_POSITION_SPEED);
}
