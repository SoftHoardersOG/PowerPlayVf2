package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.revex.ExpansionHubEx;


public class HardwareUtils {
    public static HardwareMap hardwareMap;

    public static DcMotor getDc(String name) {
        return hardwareMap.get(DcMotor.class, name);
    }

    public static DcMotorEx getDcEx(String name) {
        return hardwareMap.get(DcMotorEx.class, name);
    }

    public static Servo getServo(String name) {
        return hardwareMap.get(Servo.class, name);
    }

    public static ExpansionHubEx getExpansionHub(String name){
        return hardwareMap.get(ExpansionHubEx.class, name);
    }
}