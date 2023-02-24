package org.firstinspires.ftc.teamcode.Hardware;

import static org.firstinspires.ftc.teamcode.Hardware.HardwareUtils.*;

import android.text.method.Touch;

import com.qualcomm.hardware.broadcom.BroadcomColorSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.AccelerationSensor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRGyro;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.RevColorSensor.RevColorSensorV3;
import org.firstinspires.ftc.teamcode.Hardware.revex.RevBulkData;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Place;
import org.firstinspires.ftc.teamcode.TeleOp.ActionManager;
import org.firstinspires.ftc.teamcode.Utils.ActionDelayer;
import org.firstinspires.ftc.teamcode.Utils.Sleep;


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
    public static AnalogInput potentiometer;
    public static RevColorSensorV3 sensor;
    public static TouchSensor magneticSensor;
    public static IntegratingGyroscope gyro;
    public static ModernRoboticsI2cGyro modernRoboticsI2cGyro;

    public static void init(HardwareMap hardwareMap, Telemetry _telemetry) {
        HardwareUtils.hardwareMap = hardwareMap;
        telemetry = _telemetry;
        frontLeft = getDc("frontLeft");
        frontRight = getDc("frontRight");
        backLeft = getDc("backLeft");
        backRight = getDc("backRight");
        leftSlide =getDc("leftSlide");
        rightSlide= getDc("rightSlide");
        frontClaw = getServo("frontClaw");
        backClaw = getServo("backClaw");
        frontClawAngle = getServo("frontClawAngle");
        backClawAngle = getServo("backClawAngle");
        frontClawLift = getServo("frontClawLift");
        backSlide = getDcEx("backSlide");
        turret = getServo("turret");
        sensor = getColorSensor("sensor");
        potentiometer = getAnalogInput("potentiometer");
        magneticSensor = getTouchSensor("magneticSensor");
        modernRoboticsI2cGyro = getGyro("gyro");
        gyro=(IntegratingGyroscope)modernRoboticsI2cGyro;
        telemetry.log().add("Hardware mapping done!");
        telemetry.update();
    }

    public static void configureTeleOp(){
        backSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backSlide.setPower(1);
        rightSlide.setPower(1);
        leftSlide.setPower(1);
        backSlide.setTargetPosition(0);
        rightSlide.setTargetPosition(0);
        leftSlide.setTargetPosition(0);
        MotorConfigurationType motorConfigurationType = backSlide.getMotorType().clone();
        motorConfigurationType.setGearing(5.2);
        motorConfigurationType.setTicksPerRev(145.1);
        motorConfigurationType.setMaxRPM(1150);
        backSlide.setMotorType(motorConfigurationType);
        backSlide.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, new PIDFCoefficients(9, 0,0,0));
        backSlide.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(30, 0,10,0));
        ((ServoImplEx)(frontClawAngle)).setPwmRange(new PwmControl.PwmRange(500, 2500));
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        initPositions();
        telemetry.log().add("Hardware configuring done!");
        telemetry.update();
    }

    public static void configureAuto(){
        backSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backSlide.setPower(1);
        rightSlide.setPower(1);
        leftSlide.setPower(1);
        backSlide.setTargetPosition(0);
        rightSlide.setTargetPosition(0);
        leftSlide.setTargetPosition(0);
        MotorConfigurationType motorConfigurationType = backSlide.getMotorType().clone();
        motorConfigurationType.setGearing(5.2);
        motorConfigurationType.setTicksPerRev(145.1);
        motorConfigurationType.setMaxRPM(1150);
        backSlide.setMotorType(motorConfigurationType);
        backSlide.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, new PIDFCoefficients(9, 0,0,0));
        backSlide.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(30, 0,10,0));
        ((ServoImplEx)(frontClawAngle)).setPwmRange(new PwmControl.PwmRange(500, 2500));
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ActionManager.transfer=true;
        ActionManager.cycling=true;
        initPositionsAuto();
        telemetry.log().add("Hardware configuring done!");
        telemetry.update();
    }

    private static void initPositions(){
        Intake.open();
        Intake.idle();
        Sleep.ms(500);
        Place.transfer();
        Place.open();
        Place.turretToPosition(2);
        Intake.liftToPosition(0);
    }

    private static void initPositionsAuto(){
        Place.transfer();
        Place.open();
        Intake.idle();
        Sleep.ms(400);
        Intake.open();
        Place.turretToPosition(2);
        Intake.liftToPosition(0);
    }

}