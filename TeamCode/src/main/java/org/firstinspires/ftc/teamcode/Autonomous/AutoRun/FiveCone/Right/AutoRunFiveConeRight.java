package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Right;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.A;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoCases;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ImageDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PoseStorage;
import org.firstinspires.ftc.teamcode.Autonomous.B;
import org.firstinspires.ftc.teamcode.Autonomous.C;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Place;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.ActionDelayer;

public class AutoRunFiveConeRight implements Runnable {

    private SampleMecanumDrive sampleMecanumDrive;
    public static AutoCases detectedCase;
    private LinearOpMode opMode;

    public AutoRunFiveConeRight(SampleMecanumDrive sampleMecanumDrive, LinearOpMode opMode) {
        this.sampleMecanumDrive = sampleMecanumDrive;
        this.opMode = opMode;
        FiveConeRightTrajectories.setDrive(sampleMecanumDrive);
        FiveConeRightTrajectories.InitTrajectories();
    }

    @Override
    public void run() {
        switch (ImageDetection.beaconCaseOut) {
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

        ImageDetection.camera.stopStreaming();
        intake(sampleMecanumDrive);
        Place.highAuto();
        Hardware.turret.setPosition(0.35);
        Place.place();
        opMode.sleep(100);
        Place.close();
        opMode.sleep(520);
        Place.open();
        opMode.sleep(300);
        Place.low();
        Place.transfer();
        Place.turretToPosition(2);
        Intake.currentPosition=5;
        for (int i=1;i<=5;i++){
            Intake.collect();
            Intake.changeLiftToPosition(-1);
            opMode.sleep(350);
            Intake.close();
            opMode.sleep(100);
            Intake.neutralSlides();
            Intake.transfer();
            Intake.liftToPosition(5);
            if (i==1) {
                opMode.sleep(490);
            }
            else{
                opMode.sleep(530);
            }
            Intake.open();
            opMode.sleep(130);
            Hardware.turret.setPosition(0.35);
            Intake.idle();
            Place.place();
            Place.highAuto();
            opMode.sleep(200);
            Place.close();
            opMode.sleep(440);
            Place.open();
            opMode.sleep(150);
            Place.transfer();
            Place.turretToPosition(2);
            opMode.sleep(50);
            Place.low();
        }
        park(sampleMecanumDrive);
    }

    public void intake(SampleMecanumDrive drive) {
        drive.followTrajectory(FiveConeRightTrajectories.IntakeTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(FiveConeRightTrajectories.IntakeTrajectory2(drive.getPoseEstimate()));
    }

    public void park(SampleMecanumDrive drive){
        drive.followTrajectory(FiveConeRightTrajectories.ParkTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(FiveConeRightTrajectories.ParkTrajectory2(drive.getPoseEstimate()));
    }

}

