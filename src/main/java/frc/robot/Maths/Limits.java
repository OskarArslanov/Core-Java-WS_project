package frc.robot.Maths;

public class Limits {
    
    public static double getLimitedValue(double value, double MIN_LIMIT, double MAX_LIMIT){
        if (value > MAX_LIMIT) {
            value = MAX_LIMIT;
        } else if (value < MIN_LIMIT) {
            value = MIN_LIMIT;
        }
        return value;
    }

    public static boolean isValueInLimits(double value, double MIN_LIMIT, double MAX_LIMIT){
        return value >= MIN_LIMIT && value <= MAX_LIMIT;
    }
}
