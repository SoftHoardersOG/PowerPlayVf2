package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveConeMID.Left;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.A;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Right.FiveConeRightTrajectories;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveConeMID.Right.FiveConeMIDRightTrajectories;
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


public class AutoRunFiveConeMIDLeft implements Runnable {

    private SampleMecanumDrive sampleMecanumDrive;
    public static AutoCases detectedCase;
    private LinearOpMode opMode;
    public static Pose2d reposePose;

    public AutoRunFiveConeMIDLeft(SampleMecanumDrive sampleMecanumDrive, LinearOpMode opMode) {
        this.sampleMecanumDrive = sampleMecanumDrive;
        this.opMode = opMode;
        FiveConeMIDLeftTrajectories.setDrive(sampleMecanumDrive);
        FiveConeMIDLeftTrajectories.InitTrajectories();
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
        ActionDelayer.time(0, ()-> AprilTagImageDetection.camera.stopStreaming());
        detectedCase = new A();
        intake(sampleMecanumDrive);
        Hardware.backClawAngle.setPosition(0.36);
        opMode.sleep(100);
        Place.close();
        Hardware.turret.setPosition(0.98);
        opMode.sleep(450);
        Place.highAutoLeft();
        opMode.sleep(520);
        Hardware.backClawAngle.setPosition(0.11);
        opMode.sleep(100);
        Place.open();
        opMode.sleep(100);
        Place.transfer();
        opMode.sleep(340);
        Place.low();
        Place.turretToPosition(2);
        Intake.currentPosition = 5;
        Intake.changeLiftToPosition(-1);
        Hardware.frontClawAngle.setPosition(0.65);
        reposePose = sampleMecanumDrive.getPoseEstimate();
        Hardware.rightSlide.setTargetPosition(-760);
        Hardware.leftSlide.setTargetPosition(760);
        for (int i = 1; i <= 5; i++) {
            if (i > 1) {
                Hardware.rightSlide.setTargetPosition(-760);
                Hardware.leftSlide.setTargetPosition(760);
            }
            if(i==4 || i==5) {
                Hardware.frontClawAngle.setPosition(0.66);
                Hardware.rightSlide.setTargetPosition(-760);
                Hardware.leftSlide.setTargetPosition(760);
            }
            opMode.sleep(400);
            Intake.close();
            opMode.sleep(250);
            Intake.idle();
            Hardware.backClawAngle.setPosition(0.92);
            opMode.sleep(100);
            Intake.liftToPosition(0);
            opMode.sleep(350);
            Hardware.rightSlide.setTargetPosition(-5);
            Hardware.leftSlide.setTargetPosition(5);
            opMode.sleep(800);
            Intake.transfer();
            while (!Potentiometer.isFrontArmUp());
            opMode.sleep(100);
            Intake.open();
            opMode.sleep(100);
            Intake.idle();
            Hardware.rightSlide.setTargetPosition(-300);
            Hardware.leftSlide.setTargetPosition(300);
            repose(sampleMecanumDrive);
            Hardware.backClawAngle.setPosition(0.36);
            Hardware.turret.setPosition(0.98);
            opMode.sleep(500);
            Place.highAutoLeft();
            Place.close();
            opMode.sleep(120);
            opMode.sleep(400);
            Hardware.backClawAngle.setPosition(0.08);
            opMode.sleep(200);
            Place.open();
            opMode.sleep(90);
            Place.transfer();
            if (i != 5) {
                Hardware.frontClawAngle.setPosition(0.67);
                Intake.changeLiftToPosition(-1);
            }
            if(i ==3 || i==4)
            {
                Hardware.frontClawAngle.setPosition(0.67);
            }
            if (i==2){
                Hardware.frontClawAngle.setPosition(0.66);
            }
            opMode.sleep(280);
            Place.low();
            Place.turretToPosition(2);
            repose(sampleMecanumDrive);
        }
        park(sampleMecanumDrive);
    }

    public void intake(SampleMecanumDrive drive) {
        drive.followTrajectory(FiveConeMIDLeftTrajectories.IntakeTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(FiveConeMIDLeftTrajectories.IntakeTrajectory2(drive.getPoseEstimate()));
        drive.followTrajectory(FiveConeMIDLeftTrajectories.IntakeTrajectory3(drive.getPoseEstimate()));
    }

    public void repose(SampleMecanumDrive drive){
        drive.followTrajectory(FiveConeMIDLeftTrajectories.Repose(drive.getPoseEstimate()));
    }

    public void park(SampleMecanumDrive drive){
        drive.followTrajectory(FiveConeMIDLeftTrajectories.ParkTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(FiveConeMIDLeftTrajectories.ParkTrajectory2(drive.getPoseEstimate()));
    }

}
