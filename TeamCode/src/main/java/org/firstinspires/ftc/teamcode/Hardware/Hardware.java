package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Hardware.HardwareUtils.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.revex.ExpansionHubEx;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Place;


public class Hardware {
    public static DcMotor frontRight;
    public static DcMotor frontLeft;
    public static DcMotor backRight;
    public static DcMotor backLeft;
    public static Servo frontClaw;
    public static Servo backClaw;
    public static Servo frontClawAngle;
    public static Servo backClawAngle;
    public static Servo frontClawLift;
    public static Servo turret;
    public static DcMotorEx backSlide;
    public static Telemetry telemetry;

    public static void init(HardwareMap hardwareMap, Telemetry _telemetry) {
        HardwareUtils.hardwareMap = hardwareMap;
        telemetry = _telemetry;
        frontLeft = getDc("frontLeft");
        frontRight = getDc("frontRight");
        backLeft = getDc("backLeft");
        backRight = getDc("backRight");
        frontClaw = HardwareUtils.getServo("frontClaw");
        backClaw = HardwareUtils.getServo("backClaw");
        frontClawAngle = HardwareUtils.getServo("frontClawAngle");
        backClawAngle = HardwareUtils.getServo("backClawAngle");
        frontClawLift = HardwareUtils.getServo("frontClawLift");
        backSlide = HardwareUtils.getDcEx("backSlide");
        turret = HardwareUtils.getServo("turret");
        telemetry.addLine("Hardware mapping done!");
    }

    public static void configure() {
        Hardware.backSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hardware.backSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Hardware.backSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hardware.backSlide.setPower(1);
        Hardware.backSlide.setTargetPosition(0);
        Intake.open();
        Intake.idle();
        Place.transfer();
        Place.open();
        Place.turretToPosition(2);
        telemetry.addLine("Hardware configuring done!");
    }
}
///485 , -225 mid