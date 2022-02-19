package frc.robot.Maths.Common;

public class TF {
    private double[] inArray;
    private double[] outArray;

    public TF(double[] inArray, double[] outArray){
        this.inArray = inArray;
        this.outArray = outArray;
    }

    private double outValue;
    public void calculate(double inputValue) {
        if ( Limits.isValueInLimits(inputValue, inArray[0], inArray[inArray.length - 1] )) {
            int counter = 0;
            while (true) {
                if (isInRangeBetween2Values(inputValue, inArray, counter)) {
                    outValue = endValueIn(inputValue, counter);
                    break;
                }
                counter++;
            }
        } else {
            outValue = Limits.getLimitedValue(inputValue, outArray[0], outArray[outArray.length - 1]);
        }
    }

    public double getOutput() {
        return outValue;
    }

    private double endValueIn(double inputValue, int counter) { // some Magic
        double deltaStartValues = inArray[counter + 1] - inArray[counter];
        double deltaInputStartValues = inputValue - inArray[counter];
        double kStartValues = deltaInputStartValues / deltaStartValues;
        double deltaEndValues = outArray[counter + 1] - outArray[counter];
        double multiplyK = kStartValues*deltaEndValues;
        return multiplyK+outArray[counter];
    }

    private boolean isInRangeBetween2Values(double inputValue, double[] inArray, int counter ) { // some Magic
        boolean isInputLessOrEqualNextValue = inputValue <= inArray[counter+1];
        boolean isInputGreaterOrEqualPrevValue = inputValue >= inArray[counter];
        return isInputGreaterOrEqualPrevValue && isInputLessOrEqualNextValue;
    }

    final static private double[] ANGLE_DELTA = { -10, -3, 3, 10 };
    final static private double[] ANGLE_SPEED = { -30, -10, 10, 30 };
    final static public TF GYROSCOPE_ANGLE_TF = new TF(ANGLE_DELTA, ANGLE_SPEED);

    final static private double[] DISTANCE_DELTA_END = { 0, 3, 10, 15, 20 };
    final static private double[] DISTANCE_SPEED_END = { 0, 7, 15, 40, 70 };
    final static public TF DISTANCE_TF_END = new TF(DISTANCE_DELTA_END, DISTANCE_SPEED_END);

    final static private double[] DISTANCE_DELTA_CONTINUOUS = { 0, 50, 150 };
    final static private double[] DISTANCE_SPEED_CONTINUOUS = { 35, 50, 70 };
    final static public TF DISTANCE_TF_CONTINUOUS = new TF(DISTANCE_DELTA_CONTINUOUS, DISTANCE_SPEED_CONTINUOUS);

    final static private double[] TIMER_IN = { 0, 0.05, 0.6, 1 };
    final static private double[] K_OUT = { 0, 0.1, 0.4, 1 };
    final static public TF TIMER_TO_K = new TF(TIMER_IN, K_OUT);

    final static private double[] CORRECT_Z_POSITION_DELTA = {-10, -3, -1, 1, 3, 10};
    final static private double[] CORRECT_Z_POSITION_SPEED = {-15, -10, -3, 3, 10, 15};
    final static public TF SENSORS_ANGLE_TF = new TF(CORRECT_Z_POSITION_DELTA, CORRECT_Z_POSITION_SPEED);

    final static private double[] DELTA_SONAR_END = {-40, -13, -1, 1, 13, 40};
    final static private double[] SPEED_SONAR_END = {-75, -30, -3, 3, 30, 75};
    final static public TF SONAR_END = new TF(DELTA_SONAR_END, SPEED_SONAR_END);

    final static private double[] DELTA_SONAR_CONT = {-40, -25, -10, 10, 25, 40};
    final static private double[] SPEED_SONAR_CONT = {-70, -60, -50, 50, 60, 70};
    final static public TF SONAR_CONT = new TF(DELTA_SONAR_CONT, SPEED_SONAR_CONT);

    final static private double[] DELTA_SHARP_END = {-40, -13, -1, 1, 13, 40};
    final static private double[] SPEED_SHARP_END = {-75, -30, -3, 3, 30, 75};
    final static public TF SHARP_END = new TF(DELTA_SHARP_END, SPEED_SHARP_END);

    final static private double[] DELTA_SHARP_CONT = {-30, -20, -10, 10, 20, 30};
    final static private double[] SPEED_SHARP_CONT = {-70, -60, -50, 50, 60, 70};
    final static public TF SHARP_CONT = new TF(DELTA_SHARP_CONT, SPEED_SHARP_CONT);
}
