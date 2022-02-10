package frc.robot.UserMath;

public abstract class Limits {
    public static double limitOf(double value, double MIN_LIMIT, double MAX_LIMIT){
        if (value > MAX_LIMIT) {
            value = MAX_LIMIT;
        } else if (value < MIN_LIMIT) {
            value = MIN_LIMIT;
        }
        return value;
    }

    public static boolean inLimit(double value, double MIN_LIMIT, double MAX_LIMIT){
        return value >= MIN_LIMIT && value <= MAX_LIMIT;
    }
}
