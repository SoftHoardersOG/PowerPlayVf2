package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;

public class Gyro {

    private static ElapsedTime timer = new ElapsedTime();

    public static void calibrate(){
        Hardware.modernRoboticsI2cGyro.calibrate();
        timer.reset();
        while (Hardware.modernRoboticsI2cGyro.isCalibrating())  {
            Hardware.telemetry.addData("calibrating", "%s", Math.round(timer.seconds())%2==0 ? "|.." : "..|");
            Hardware.telemetry.update();
            Sleep.ms(80);
        }
        Hardware.telemetry.log().add("Gyro Calibrated. Press Start.");
        Hardware.telemetry.clear(); Hardware.telemetry.update();
    }

    public static double getHeadingAutoDegrees(){
        return Hardware.modernRoboticsI2cGyro.getHeading();
    }

    public static double getHeadingAutoRadians(){
        return Math.toRadians(Hardware.modernRoboticsI2cGyro.getHeading());
    }

}
