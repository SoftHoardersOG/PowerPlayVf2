package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Place;
import org.firstinspires.ftc.teamcode.Utils.ActionDelayer;
import org.firstinspires.ftc.teamcode.Utils.OneTap;

public class ActionManager {
    private static Gamepad gamepad1;
    private static Gamepad gamepad2;

    private static final OneTap oneTapLB = new OneTap();
    private static final OneTap oneTapRB = new OneTap();

    public static void control(Gamepad _gamepad1, Gamepad _gamepad2) {
        gamepad1 = _gamepad1;
        gamepad2 = _gamepad2;
        Movement.run(gamepad1);
        collectPose();
        collectClaw();
        transfer();
        placeAndReturn();
        turretMovement();
        slideHigh();
        slideMid();
        frontSlides();
        lowPlace();
        groundPlace();
        raiseArm();
        frontLift();
        testTransfer();
    }
    public static void lowPlace(){
        if(gamepad2.cross){
            ActionDelayer.time(0,Place::lowJunkPoz);
            ActionDelayer.time(0,Place::low);
        }
    }

    public static void groundPlace(){
        if(gamepad1.dpad_left){
            ActionDelayer.time(0,Intake::collect);
        }
    }

    public static void collectPose() {
        if (gamepad1.circle) {
            ActionDelayer.time(0, Intake::collect);
            ActionDelayer.time(0, Intake::open);
        }
        if (gamepad1.touchpad){
            Hardware.frontClawAngle.setPosition(0.67);
        }
    }

    public static void collectClaw() {
        if (gamepad1.cross) {
            ActionDelayer.time(0, Intake::close);
            ActionDelayer.time(200, Intake::idle);
        }
    }

    public static void raiseArm(){
        if (gamepad1.dpad_right){
            Intake.idle();
            Intake.open();
        }
    }

    public static void transfer() {
        if (gamepad1.square) {
            ActionDelayer.time(0, Intake::close);
            ActionDelayer.time(120, Intake::neutralSlides);
            ActionDelayer.time(120, Intake::transfer);
            ActionDelayer.time(100, ()->{
                Intake.liftToPosition(5);
            });
            ActionDelayer.time(570, Intake::open);
            ActionDelayer.time(670, Intake::idle);

            ActionDelayer.time(660, Place::place);
            ActionDelayer.time(800, Place::close);
            ActionDelayer.time(930, ()->{
                Intake.liftToPosition(0);
                Intake.currentPosition=0;
            });
        }
    }

    public static void placeAndReturn() {
        if (gamepad1.triangle) {
            Intake.collect();
            ActionDelayer.time(0, Place::open);
            ActionDelayer.time(100, Place::transfer);
            ActionDelayer.time(200, ()->{
                Place.turretToPosition(2);
                Place.low();
            });
        }
    }

    public static void turretMovement() {
        if (gamepad2.dpad_up) {
            Place.turretToPosition(3);
        }
        if(gamepad2.dpad_left){
            Place.turretToPosition(0);
        }
        if(gamepad2.dpad_right){
            Place.turretToPosition(1);
        }
    }

    public static void slideHigh(){
        if (gamepad2.triangle){
            ActionDelayer.time(0, Place::high);
            ActionDelayer.time(0,Place::place);
        }
    }

    public static void slideMid(){
        if (gamepad2.square){
            ActionDelayer.time(0, Place::mid);
            ActionDelayer.time(0,Place::place);
        }
    }
    public static void frontSlides(){
        if (gamepad1.dpad_up){
            ActionDelayer.time(0,Intake::goToMax);

        }
        if(gamepad1.dpad_down){
            ActionDelayer.time(0,Intake::idle);
            ActionDelayer.time(0,Intake::neutralSlides);
        }
    }

    public static void frontLift(){
        if (oneTapLB.onPress(gamepad1.left_bumper)){
            Intake.changeLiftToPosition(-1);
        }
        if (oneTapRB.onPress(gamepad1.right_bumper)){
            Intake.changeLiftToPosition(1);
        }
    }

    public static void testTransfer(){
        if (gamepad1.left_stick_button){
            ActionDelayer.time(0, Intake::close);
            ActionDelayer.time(120, Intake::neutralSlides);
            ActionDelayer.time(120, Intake::transfer);
            ActionDelayer.time(100, ()->{
                Intake.liftToPosition(5);
            });
            ActionDelayer.condition(()-> Hardware.sensor.getDistance(DistanceUnit.CM)<3, ()->{
                ActionDelayer.time(0, Intake::open);
                ActionDelayer.time(120, Intake::idle);

                ActionDelayer.time(100, Place::place);
                ActionDelayer.time(230, Place::close);
                ActionDelayer.time(360, ()->{
                    Intake.liftToPosition(0);
                    Intake.currentPosition=0;
                });
            });
        }
    }
}
