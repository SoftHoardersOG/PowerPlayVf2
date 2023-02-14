package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Place;
import org.firstinspires.ftc.teamcode.Utils.ActionDelayer;
import org.firstinspires.ftc.teamcode.Utils.OneTap;
import org.firstinspires.ftc.teamcode.Utils.Potentiometer;

public class ActionManager {
    private static Gamepad gamepad1;
    private static Gamepad gamepad2;

    private static final OneTap oneTapLB = new OneTap();
    private static final OneTap oneTapRB = new OneTap();
    private static final OneTap oneTapChangeTransfer = new OneTap();
    private static final OneTap oneTapChangeCycle = new OneTap();
    public static boolean transfer = true;
    public static boolean cycling = true;

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
        frontSlidesMax();
        lowPlace();
        groundPlace();
        raiseArm();
        frontLift();
        changeTransfer();
        changeCycle();
        checkCollectPose();
        beaconPose();
    }

    public static void checkCollectPose(){
        if (Intake.isArmCollect()&&Intake.areSlidesExtended()){
            Intake.higherCollectPose();
        }
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
            if (transfer) {
                ActionDelayer.time(0, Intake::close);
                ActionDelayer.time(150, Intake::neutralSlides);
                ActionDelayer.time(150, Intake::idle);
                ActionDelayer.time(200, ()->{
                    Intake.liftToPosition(0);
                    Intake.currentPosition=0;
                });
                ActionDelayer.time(150, ()-> ActionDelayer.condition(()->(Hardware.leftSlide.getCurrentPosition()<50||Hardware.rightSlide.getCurrentPosition()>-50) && Potentiometer.isFrontArmUp(),()->{
                    ActionDelayer.time(0, Intake::transfer);
                    ActionDelayer.time(50, Intake::open);
                    ActionDelayer.time(150, Intake::idle);
                    ActionDelayer.time(160, Place::place);
                    ActionDelayer.time(350, Place::close);
                }));
            }
            else{
                ActionDelayer.time(0, Intake::close);
                ActionDelayer.time(150, Intake::neutralSlides);
                ActionDelayer.time(150, Intake::idle);
                ActionDelayer.time(150, ()-> ActionDelayer.condition(()->Hardware.leftSlide.getCurrentPosition()<50||Hardware.rightSlide.getCurrentPosition()>-50, ()->{
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
                }));
            }
        }
    }

    public static void placeAndReturn() {
        if (gamepad1.triangle&&cycling) {
            Intake.collect();
            ActionDelayer.time(0, Place::open);
            ActionDelayer.time(300, ()->{
                Place.turretToPosition(2);
                Place.low();
            });
            ActionDelayer.time(150, Place::transfer);
        }
        else if (gamepad1.triangle&&!cycling){
            Intake.collect();
            ActionDelayer.time(0, () -> Hardware.backClawAngle.setPosition(0.06));
            ActionDelayer.time(100, Place::open);
            ActionDelayer.time(200, Place::transfer);
            ActionDelayer.time(500, ()->{
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
    public static void frontSlidesMax(){
        if (gamepad1.dpad_up){
            ActionDelayer.time(0,Intake::frontSlideMax);

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

    public static void changeTransfer(){
        if (oneTapChangeTransfer.onPress(gamepad1.left_trigger>0.5)){
            transfer = !transfer;
        }
    }
    public static void changeCycle(){
        if (oneTapChangeCycle.onPress(gamepad2.touchpad)){
            cycling = !cycling;
        }
    }
    public static void beaconPose(){
        if (gamepad1.touchpad){
            Hardware.frontClawAngle.setPosition(0.73);
        }
    }
}
