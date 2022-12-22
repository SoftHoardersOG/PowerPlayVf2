package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Hardware.HardwareUtils.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

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
    public static DcMotor leftSlide;
    public static DcMotor rightSlide;

    public static void init(HardwareMap hardwareMap, Telemetry _telemetry) {
        HardwareUtils.hardwareMap = hardwareMap;
        telemetry = _telemetry;
        frontLeft = getDc("frontLeft");
        frontRight = getDc("frontRight");
        backLeft = getDc("backLeft");
        backRight = getDc("backRight");
        leftSlide =getDc("leftSlide");
        rightSlide= getDc("rightSlide");
        frontClaw = HardwareUtils.getServo("frontClaw");
        backClaw = HardwareUtils.getServo("backClaw");
        frontClawAngle = HardwareUtils.getServo("frontClawAngle");
        backClawAngle = HardwareUtils.getServo("backClawAngle");
        frontClawLift = HardwareUtils.getServo("frontClawLift");
        backSlide = HardwareUtils.getDcEx("backSlide");
        turret = HardwareUtils.getServo("turret");
        telemetry.addLine("Hardware mapping done!");
    }

    public static void configure(){
        backSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backSlide.setPower(0.8);
        rightSlide.setPower(0.8);
        leftSlide.setPower(0.8);
        backSlide.setTargetPosition(0);
        rightSlide.setTargetPosition(0);
        leftSlide.setTargetPosition(0);
        MotorConfigurationType motorConfigurationType = backSlide.getMotorType().clone();
        motorConfigurationType.setGearing(5.2);
        motorConfigurationType.setTicksPerRev(145.1);
        motorConfigurationType.setMaxRPM(1150);
        backSlide.setMotorType(motorConfigurationType);
        backSlide.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, new PIDFCoefficients(8, 0,0,0));
        backSlide.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(30, 0,10,0));
        ((ServoImplEx)(frontClawAngle)).setPwmRange(new PwmControl.PwmRange(500, 2500));
        Intake.open();
        Intake.idle();
        long currenttime = System.currentTimeMillis();
        while (System.currentTimeMillis()-currenttime<300);
        Place.transfer();
        Place.open();
        Place.turretToPosition(2);
        telemetry.addLine("Hardware configuring done!");
    }
}