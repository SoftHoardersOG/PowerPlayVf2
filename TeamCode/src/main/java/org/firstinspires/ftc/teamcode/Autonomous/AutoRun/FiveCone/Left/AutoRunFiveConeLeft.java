package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Left;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.A;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Right.FiveConeRightTrajectories;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveConeMID.Left.FiveConeMIDLeftTrajectories;
import org.firstinspires.ftc.teamcode.Autonomous.B;
import org.firstinspires.ftc.teamcode.Autonomous.C;
import org.firstinspires.ftc.teamcode.Autonomous.Tests.AprilTagImageDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoCases;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ImageDetection;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Place;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.ActionDelayer;
import org.firstinspires.ftc.teamcode.Utils.Potentiometer;


public class AutoRunFiveConeLeft implements Runnable {

    private SampleMecanumDrive sampleMecanumDrive;
    public static AutoCases detectedCase;
    public static Pose2d reposePose;
    private LinearOpMode opMode;

    public AutoRunFiveConeLeft(SampleMecanumDrive sampleMecanumDrive, LinearOpMode opMode) {
        this.sampleMecanumDrive = sampleMecanumDrive;
        this.opMode = opMode;
        FiveConeLeftTrajectories.setDrive(sampleMecanumDrive);
        FiveConeLeftTrajectories.InitTrajectories();
    }

    @Override
    public void run() {
        switch (AprilTagImageDetection.beaconCaseOut) {
            case One:
                detectedCase = new A();
                break;
            case Two:
                detectedCase = new B();
                break;
            case Three:
                detectedCase = new C();
                break;
        }
        ActionDelayer.time(0, ()->AprilTagImageDetection.camera.stopStreaming());
        intake(sampleMecanumDrive);
        Hardware.backClawAngle.setPosition(0.36);
        opMode.sleep(100);
        Place.close();
        Hardware.turret.setPosition(0.06);
        opMode.sleep(450);
        Place.highAutoLeftPreload();
        opMode.sleep(720);
        Hardware.backClawAngle.setPosition(0.09);
        opMode.sleep(100);
        Place.open();
        opMode.sleep(100);
        Place.transfer();
        reposePose = sampleMecanumDrive.getPoseEstimate();
        opMode.sleep(340);
        Place.low();
        Place.transfer();
        Place.turretToPosition(2);
        Hardware.rightSlide.setTargetPosition(-265);
        Hardware.leftSlide.setTargetPosition(265);
        Intake.currentPosition = 5;
        Intake.changeLiftToPosition(-1);
        Hardware.frontClawAngle.setPosition(0.66);
        for (int i = 1; i <= 5; i++) {
            if (i > 1) {
                Hardware.rightSlide.setTargetPosition(-268);
                Hardware.leftSlide.setTargetPosition(268);
            }
            if (i == 3 || i==4) {
                Hardware.rightSlide.setTargetPosition(-270);
                Hardware.leftSlide.setTargetPosition(270);
            }
            if(i==5)
            {
                Hardware.frontClawAngle.setPosition(0.72);
                Hardware.rightSlide.setTargetPosition(-270);
                Hardware.leftSlide.setTargetPosition(270);
            }
            if (i==1)
                opMode.sleep(330);
            opMode.sleep(100);
            Intake.close();
            opMode.sleep(150);
            Intake.idle();
            Hardware.backClawAngle.setPosition(0.94);
            opMode.sleep(100);
            Hardware.rightSlide.setTargetPosition(Hardware.rightSlide.getTargetPosition()+10);
            Hardware.leftSlide.setTargetPosition(Hardware.leftSlide.getTargetPosition()-10);
            Intake.liftToPosition(0);
            opMode.sleep(350);
            Hardware.rightSlide.setTargetPosition(-5);
            Hardware.leftSlide.setTargetPosition(5);
            opMode.sleep(250);
            Intake.transfer();
            while (!Potentiometer.isFrontArmUp());
            opMode.sleep(100);
            Intake.open();
            opMode.sleep(100);
            Intake.idle();
            Hardware.backClawAngle.setPosition(0.36);
            Hardware.turret.setPosition(0.06);
            opMode.sleep(200);
            Place.close();
            opMode.sleep(300);
            Place.highAutoLeft();
            opMode.sleep(120);
            opMode.sleep(400);
            Hardware.backClawAngle.setPosition(0.08);
            opMode.sleep(200);
            Place.open();
            opMode.sleep(90);
            Place.transfer();
            if (i < 5) {
                Hardware.frontClawAngle.setPosition(0.71);
                Intake.changeLiftToPosition(-1);
            }
            if(i ==3 || i==4)
            {
                Hardware.frontClawAngle.setPosition(0.72);
            }
            opMode.sleep(280);
            Place.low();
            Place.turretToPosition(2);
            if (i<5) {
                repose(sampleMecanumDrive);
            }
        }
        park(sampleMecanumDrive);
    }

    public void intake(SampleMecanumDrive drive) {
        drive.followTrajectory(FiveConeLeftTrajectories.IntakeTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(FiveConeLeftTrajectories.IntakeTrajectory2(drive.getPoseEstimate()));
    }

    public void repose(SampleMecanumDrive drive){
        drive.followTrajectory(FiveConeLeftTrajectories.Repose(drive.getPoseEstimate()));
    }

    public void park(SampleMecanumDrive drive){
        drive.followTrajectory(FiveConeLeftTrajectories.ParkTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(FiveConeLeftTrajectories.ParkTrajectory2(drive.getPoseEstimate()));
    }

}
