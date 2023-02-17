package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.Park.Left;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.A;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Left.FiveConeLeftTrajectories;
import org.firstinspires.ftc.teamcode.Autonomous.B;
import org.firstinspires.ftc.teamcode.Autonomous.C;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoCases;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ImageDetection;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Place;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.ActionDelayer;
import org.firstinspires.ftc.teamcode.Utils.Potentiometer;

public class AutoRunParkLeft implements Runnable{
    private SampleMecanumDrive sampleMecanumDrive;
    public static AutoCases detectedCase;
    private LinearOpMode opMode;

    public AutoRunParkLeft(SampleMecanumDrive sampleMecanumDrive, LinearOpMode opMode) {
        this.sampleMecanumDrive = sampleMecanumDrive;
        this.opMode = opMode;
        FiveConeLeftTrajectories.setDrive(sampleMecanumDrive);
        FiveConeLeftTrajectories.InitTrajectories();
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
        ActionDelayer.time(0, ()->ImageDetection.camera.stopStreaming());

        intake(sampleMecanumDrive);
        Hardware.backClawAngle.setPosition(0.36);
        opMode.sleep(100);
        Place.close();
        Hardware.turret.setPosition(0.13);
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
        Place.transfer();
        Place.turretToPosition(2);

        park(sampleMecanumDrive);
    }

    public void intake(SampleMecanumDrive drive) {
        drive.followTrajectory(ParkLeftTrajectories.IntakeTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(ParkLeftTrajectories.IntakeTrajectory2(drive.getPoseEstimate()));
    }

    public void park(SampleMecanumDrive drive){
        drive.followTrajectory(ParkLeftTrajectories.ParkTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(ParkLeftTrajectories.ParkTrajectory2(drive.getPoseEstimate()));
    }
}
