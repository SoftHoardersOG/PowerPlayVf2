package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveConeMidJunction;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.A;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Left.FiveConeLeftTrajectories;
import org.firstinspires.ftc.teamcode.Autonomous.B;
import org.firstinspires.ftc.teamcode.Autonomous.C;
import org.firstinspires.ftc.teamcode.Autonomous.Tests.AprilTagImageDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoCases;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Place;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.ActionDelayer;
import org.firstinspires.ftc.teamcode.Utils.Potentiometer;

public class AutoRunFiveConeMidJunctionLeft implements Runnable{
    private SampleMecanumDrive sampleMecanumDrive;
    public static AutoCases detectedCase;
    private LinearOpMode opMode;

    public AutoRunFiveConeMidJunctionLeft(SampleMecanumDrive sampleMecanumDrive, LinearOpMode opMode) {
        this.sampleMecanumDrive = sampleMecanumDrive;
        this.opMode = opMode;
        FiveConeMidJunctionLeftTrajectories.setDrive(sampleMecanumDrive);
        FiveConeMidJunctionLeftTrajectories.InitTrajectories();
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
        Hardware.turret.setPosition(1);
        opMode.sleep(450);
        Hardware.backSlide.setTargetPosition(-215);
        opMode.sleep(520);
        Hardware.backClawAngle.setPosition(0.11);
        opMode.sleep(100);
        Place.open();
        opMode.sleep(100);
        Place.transfer();
        opMode.sleep(340);
        Place.low();
        Place.transfer();
        Place.turretToPosition(2);
        Hardware.rightSlide.setTargetPosition(-322);
        Hardware.leftSlide.setTargetPosition(322);
        Intake.currentPosition = 5;
        Intake.changeLiftToPosition(-1);
        Hardware.frontClawAngle.setPosition(0.66);
        for (int i = 1; i <= 5; i++) {
            if (i > 1) {
                Hardware.rightSlide.setTargetPosition(-322);
                Hardware.leftSlide.setTargetPosition(322);
            }
            if(i==4 || i==5)
            {
                Hardware.frontClawAngle.setPosition(0.72);
                Hardware.rightSlide.setTargetPosition(-322);
                Hardware.leftSlide.setTargetPosition(322);
            }
            opMode.sleep(450);
            Intake.close();
            opMode.sleep(150);
            Intake.idle();
            Hardware.backClawAngle.setPosition(0.92);
            opMode.sleep(100);
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
            Hardware.turret.setPosition(1);
            opMode.sleep(200);
            Place.close();
            opMode.sleep(300);
            Hardware.backSlide.setTargetPosition(-215);
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
        }
        park(sampleMecanumDrive);
    }

    public void intake(SampleMecanumDrive drive) {
        drive.followTrajectory(FiveConeMidJunctionLeftTrajectories.IntakeTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(FiveConeMidJunctionLeftTrajectories.IntakeTrajectory2(drive.getPoseEstimate()));
    }

    public void park(SampleMecanumDrive drive){
        drive.followTrajectory(FiveConeMidJunctionLeftTrajectories.ParkTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(FiveConeMidJunctionLeftTrajectories.ParkTrajectory2(drive.getPoseEstimate()));
    }
}
