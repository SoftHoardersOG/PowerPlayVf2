package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.TenCone.Left;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.A;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.TenCone.Right.TenConeRightTrajectories;
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


public class AutoRunTenConeLeft implements Runnable {

    private SampleMecanumDrive sampleMecanumDrive;
    public static AutoCases detectedCase;
    private LinearOpMode opMode;

    public AutoRunTenConeLeft(SampleMecanumDrive sampleMecanumDrive, LinearOpMode opMode) {
        this.sampleMecanumDrive = sampleMecanumDrive;
        this.opMode = opMode;
        TenConeLeftTrajectories.setDrive(sampleMecanumDrive);
        TenConeLeftTrajectories.InitTrajectories();
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

        ActionDelayer.time(0, () -> AprilTagImageDetection.camera.stopStreaming());
        intake(sampleMecanumDrive);
        Place.highAuto();
        Hardware.turret.setPosition(0.1);
        Intake.currentPosition=5;
        Intake.collect();
        Intake.changeLiftToPosition(-1);
        Place.place();
        opMode.sleep(100);
        Place.close();
        opMode.sleep(520);
        Place.open();
        opMode.sleep(300);
        Place.low();
        Place.transfer();
        Place.turretToPosition(2);
        for (int i=1;i<=4;i++){
            opMode.sleep(70);
            Intake.close();
            opMode.sleep(130);
            Intake.transfer();
            Intake.liftToPosition(0);
            if (i==1) {
                opMode.sleep(460);
            }
            else{
                opMode.sleep(500);
            }
            Intake.open();
            opMode.sleep(130);
            Hardware.turret.setPosition(0.1);
            Intake.collect();
            Intake.changeLiftToPosition(-1);
            Place.highAuto();
            opMode.sleep(30);
            Place.place();
            opMode.sleep(170);
            Place.close();
            opMode.sleep(440);
            Place.open();
            opMode.sleep(150);
            Place.transfer();
            Place.turretToPosition(2);
            opMode.sleep(50);
            Place.low();
        }
        opMode.sleep(100);
        Intake.close();
        opMode.sleep(100);
        Intake.transfer();
        Intake.liftToPosition(0);
        opMode.sleep(530);
        Intake.open();
        opMode.sleep(130);
        Intake.idle();
        goToSecondStack(sampleMecanumDrive);


        Place.highAuto();
        Hardware.turret.setPosition(0.36);
        Intake.currentPosition=5;
        Intake.collect();
        Intake.changeLiftToPosition(-1);
        Place.place();
        opMode.sleep(100);
        Place.close();
        opMode.sleep(520);
        Place.open();
        opMode.sleep(300);
        Place.low();
        Place.transfer();
        Place.turretToPosition(2);
        for (int i=1;i<=5;i++) {
            opMode.sleep(100);
            Intake.close();
            opMode.sleep(100);
            Intake.transfer();
            Intake.liftToPosition(0);
            if (i == 1) {
                opMode.sleep(490);
            } else {
                opMode.sleep(530);
            }
            Intake.open();
            opMode.sleep(130);
            Hardware.turret.setPosition(0.36);
            if (i <= 4){
                Intake.collect();
                Intake.changeLiftToPosition(-1);
            }
            else{
                Intake.idle();
            }
            Place.highAuto();
            opMode.sleep(100);
            Place.place();
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
        drive.followTrajectory(TenConeLeftTrajectories.IntakeTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(TenConeLeftTrajectories.IntakeTrajectory2(drive.getPoseEstimate()));
    }

    public void park(SampleMecanumDrive drive){
        drive.followTrajectory(TenConeLeftTrajectories.ParkTrajectory(drive.getPoseEstimate()));
        //drive.followTrajectory(TenConeRightTrajectories.ParkTrajectory2(drive.getPoseEstimate()));
    }

    public void goToSecondStack(SampleMecanumDrive drive){
        drive.followTrajectory(TenConeLeftTrajectories.GoToSecondStack(drive.getPoseEstimate()));
        drive.followTrajectory(TenConeLeftTrajectories.GoToSecondStack2(drive.getPoseEstimate()));
    }

}
