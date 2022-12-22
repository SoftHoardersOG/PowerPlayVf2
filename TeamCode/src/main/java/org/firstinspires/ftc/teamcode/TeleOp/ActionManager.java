package org.firstinspires.ftc.teamcode.TeleOp;

import android.drm.DrmStore;

import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Place;
import org.firstinspires.ftc.teamcode.Utils.ActionDelayer;

public class ActionManager {
    private static Gamepad gamepad1;
    private static Gamepad gamepad2;

    public static void control(Gamepad _gamepad1, Gamepad _gamepad2) {
        gamepad1 = _gamepad1;
        gamepad2 = _gamepad2;
        collectPose();
        collectClaw();
        transfer();
        placeAndReturn();
        turretMovment();
        slideHigh();
        slideMid();
        maxLenght();
    }

    public static void collectPose() {
        if (gamepad1.circle) {
            ActionDelayer.delay(0, () -> {
                Intake.collect();
                Intake.open();
            });
        }
    }

    public static void collectClaw() {
        if (gamepad1.cross) {
            ActionDelayer.delay(0, Intake::close);
            ActionDelayer.delay(200, Intake::idle);
        }
    }

    public static void transfer() {
        if (gamepad1.square) {
            ActionDelayer.delay(0, Intake::close);
            ActionDelayer.delay(100, Intake::neutralSlides);
            ActionDelayer.delay(100, Intake::transfer);
            ActionDelayer.delay(580, Intake::open);
            ActionDelayer.delay(730, Intake::idle);
            ActionDelayer.delay(730, Place::place);
            ActionDelayer.delay(930, Place::close);

        }
    }

    public static void placeAndReturn() {
        if (gamepad1.triangle) {
            Intake.collect();
            ActionDelayer.delay(0,Place::release);
            ActionDelayer.delay(0, Place::open);
            ActionDelayer.delay(100, Place::transfer);
            ActionDelayer.delay(200, ()->{
                Place.turretToPosition(2);
                Place.low();
            });
        }

    }

    public static void turretMovment() {
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
        }
    }

    public static void slideMid(){
        if (gamepad2.square){
            ActionDelayer.delay(0, Place::mid);
        }
    }
    public static void maxLenght(){
        if (gamepad1.dpad_up){
            ActionDelayer.delay(0,Intake::goToMax);

        }
        if(gamepad1.dpad_down){
            ActionDelayer.delay(0,Intake::idle);
            ActionDelayer.delay(0,Intake::neutralSlides);
        }
    }
}
