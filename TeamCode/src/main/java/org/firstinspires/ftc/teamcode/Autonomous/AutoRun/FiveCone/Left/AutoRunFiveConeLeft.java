package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Left;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.A;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Right.FiveConeRightTrajectories;
import org.firstinspires.ftc.teamcode.Autonomous.B;
import org.firstinspires.ftc.teamcode.Autonomous.C;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoCases;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ImageDetection;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Place;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;


public class AutoRunFiveConeLeft implements Runnable {

    private SampleMecanumDrive sampleMecanumDrive;
    public static AutoCases detectedCase;
    private LinearOpMode opMode;
    public static int TMPosition = 3;
    public static double rulerAngle = 0.788;
    public static double rulerBase = 0.61;

    public AutoRunFiveConeLeft(SampleMecanumDrive sampleMecanumDrive, LinearOpMode opMode) {
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
        ImageDetection.camera.stopStreaming();
        intake(sampleMecanumDrive);
        Hardware.rightSlide.setTargetPosition(-20);
        Hardware.leftSlide.setTargetPosition(20);
        Place.highAutoLeft();
        Hardware.turret.setPosition(0.85);
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
                opMode.sleep(470);
            }
            else{
                opMode.sleep(590);
            }
            Intake.open();
            opMode.sleep(130);
            Hardware.turret.setPosition(0.85);
            Intake.idle();
            Place.highAutoLeft();
            opMode.sleep(150);
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
        drive.followTrajectory(FiveConeLeftTrajectories.IntakeTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(FiveConeLeftTrajectories.IntakeTrajectory2(drive.getPoseEstimate()));
    }

    public void park(SampleMecanumDrive drive){
        drive.followTrajectory(FiveConeLeftTrajectories.ParkTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(FiveConeLeftTrajectories.ParkTrajectory2(drive.getPoseEstimate()));
    }

}
