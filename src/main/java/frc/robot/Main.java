package frc.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.subsystems.MotorController;
import frc.robot.subsystems.SensorController;

public final class Main {
  private Main() {
  }


  public static HashMap<String, Double> motorControllerMap = new HashMap<String, Double>();
  public static HashMap<String, Double> sensorsMap = new HashMap<String, Double>();
  public static HashMap<String, Boolean> switchMap = new HashMap<String, Boolean>();

  public static void main(String... args) {
    Runnable motorControllerRunnable = new MotorController();
    Thread motorControllerThread = new Thread(motorControllerRunnable);
    motorControllerThread.setDaemon(true);
    motorControllerThread.start();

    Runnable sensorControllerRunnable = new SensorController();
    Thread sensorControllerThread = new Thread(sensorControllerRunnable);
    sensorControllerThread.setDaemon(true);
    sensorControllerThread.start();

    RobotBase.startRobot(Robot::new);
  }
}