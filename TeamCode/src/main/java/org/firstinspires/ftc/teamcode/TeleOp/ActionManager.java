package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;


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
        maxLength();
        lowPlace();
        groundPlace();
        raiseArm();
        frontLift();
    }
    public static void lowPlace(){
        if(gamepad2.cross){
            ActionDelayer.delay(0,Place::lowJunkPoz);
            ActionDelayer.delay(0,Place::low);
        }
    }

    public static void groundPlace(){
        if(gamepad1.dpad_left){
            ActionDelayer.delay(0,Intake::collect);
        }
    }

    public static void collectPose() {
        if (gamepad1.circle) {
            ActionDelayer.delay(0, Intake::collect);
            ActionDelayer.delay(0, Intake::open);
        }
    }

    public static void collectClaw() {
        if (gamepad1.cross) {
            ActionDelayer.delay(0, Intake::close);
            ActionDelayer.delay(200, Intake::idle);
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
            ActionDelayer.delay(0, Intake::close);
            ActionDelayer.delay(120, Intake::neutralSlides);
            ActionDelayer.delay(120, Intake::transfer);
            ActionDelayer.delay(100, ()->{
                Intake.liftToPosition(5);
            });
            ActionDelayer.delay(570, Intake::open);
            ActionDelayer.delay(670, Intake::idle);

            ActionDelayer.delay(660, Place::place);
            ActionDelayer.delay(920, Place::close);
            ActionDelayer.delay(930, ()->{
                Intake.liftToPosition(0);
                Intake.currentPosition=0;
            });
        }
    }

    public static void placeAndReturn() {
        if (gamepad1.triangle) {
            Intake.collect();
            ActionDelayer.delay(0, Place::open);
            ActionDelayer.delay(100, Place::transfer);
            ActionDelayer.delay(200, ()->{
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
            ActionDelayer.delay(0, Place::high);
            ActionDelayer.delay(0,Place::place);
        }
    }

    public static void slideMid(){
        if (gamepad2.square){
            ActionDelayer.delay(0, Place::mid);
            ActionDelayer.delay(0,Place::place);
        }
    }
    public static void maxLength(){
        if (gamepad1.dpad_up){
            ActionDelayer.delay(0,Intake::goToMax);

        }
        if(gamepad1.dpad_down){
            ActionDelayer.delay(0,Intake::idle);
            ActionDelayer.delay(0,Intake::neutralSlides);
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
}
