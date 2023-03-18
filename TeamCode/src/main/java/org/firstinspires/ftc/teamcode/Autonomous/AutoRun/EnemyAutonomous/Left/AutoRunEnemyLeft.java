package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.EnemyAutonomous.Left;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.A;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.EnemyAutonomous.Left.EnemyLeftTrajectories;
import org.firstinspires.ftc.teamcode.Autonomous.B;
import org.firstinspires.ftc.teamcode.Autonomous.C;
import org.firstinspires.ftc.teamcode.Autonomous.Tests.AprilTagImageDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoCases;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Place;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.ActionDelayer;


public class AutoRunEnemyLeft implements Runnable {

    private SampleMecanumDrive sampleMecanumDrive;
    public static AutoCases detectedCase;
    private LinearOpMode opMode;

    public  AutoRunEnemyLeft(SampleMecanumDrive sampleMecanumDrive, LinearOpMode opMode) {
        this.sampleMecanumDrive = sampleMecanumDrive;
        this.opMode = opMode;
        EnemyLeftTrajectories.setDrive(sampleMecanumDrive);
        EnemyLeftTrajectories.InitTrajectories();
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
        ActionDelayer.time(0, () -> AprilTagImageDetection.camera.stopStreaming());
        intake(sampleMecanumDrive);
        Hardware.backClawAngle.setPosition(0.41);
        opMode.sleep(100);
        Place.close();
        Hardware.turret.setPosition(0.10);//0.98
        opMode.sleep(450);
        Place.highAutoLeftPreload(); // -215
        opMode.sleep(350);
        Hardware.turret.setPosition(0.4);//0.98
        Hardware.backClawAngle.setPosition(0.34);
        opMode.sleep(370);
        Hardware.backClawAngle.setPosition(0.11);
        opMode.sleep(100);
        Place.open();
        opMode.sleep(50);
        Hardware.turret.setPosition(0.3);
        opMode.sleep(50);
        Place.transfer();
        Hardware.frontClawAngle.setPosition(-0.03+0.67);
        Intake.currentPosition = 5;
        Intake.changeLiftToPosition(-1);
        opMode.sleep(340);
        Place.low();
        Place.transfer();
        Place.turretToPosition(2);
        Hardware.rightSlide.setTargetPosition(-256);
        Hardware.leftSlide.setTargetPosition(256);
        Hardware.frontClawAngle.setPosition(-0.03+0.68);
        for (int i = 1; i <= 5; i++) {
            if (i == 2) {
                Hardware.rightSlide.setTargetPosition(-254);
                Hardware.leftSlide.setTargetPosition(254);
            }
            if (i == 3) {
                Hardware.frontClawAngle.setPosition(-0.03+0.71);
                Hardware.rightSlide.setTargetPosition(-250);
                Hardware.leftSlide.setTargetPosition(250);
            }
            if (i == 4) {
                Hardware.frontClawAngle.setPosition(-0.03+0.72);
                Hardware.rightSlide.setTargetPosition(-244);
                Hardware.leftSlide.setTargetPosition(244);
            }
            if (i == 5) {
                Hardware.frontClawAngle.setPosition(-0.03+0.72);
                Hardware.rightSlide.setTargetPosition(-240);
                Hardware.leftSlide.setTargetPosition(240);
            }
            if (i == 1) {
                opMode.sleep(150);
            }
            opMode.sleep(450);
            Intake.close();
            opMode.sleep(250);
            Intake.idle();
            Hardware.backClawAngle.setPosition(0.94);
            opMode.sleep(100);
            Intake.liftToPosition(0);
            opMode.sleep(350);
            Hardware.rightSlide.setTargetPosition(-10);
            Hardware.leftSlide.setTargetPosition(10);
            opMode.sleep(320);
            Intake.transfer();
            opMode.sleep(50);
            Intake.open();
            opMode.sleep(250);
            Intake.idle();
            Hardware.backClawAngle.setPosition(0.4);
            Hardware.turret.setPosition(0.10);///1
            opMode.sleep(200);
            Place.close();
            opMode.sleep(300);
            Place.highAutoLeft();
            opMode.sleep(280);
            Hardware.turret.setPosition(0.41);//0.98
            Hardware.backClawAngle.setPosition(0.34);
            opMode.sleep(390);
            Hardware.backClawAngle.setPosition(0.08);
            opMode.sleep(100);
            Place.open();
            opMode.sleep(150);
            Hardware.turret.setPosition(0.3);
            opMode.sleep(40);
            Place.transfer();
            if (i < 5) {
                Hardware.frontClawAngle.setPosition(-0.03+0.705);
                Intake.changeLiftToPosition(-1);
            }
            if (i == 3 || i == 4) {
                Hardware.frontClawAngle.setPosition(-0.03+0.69);
            }
            opMode.sleep(280);
            Place.low();
            Place.turretToPosition(2);
        }
        park(sampleMecanumDrive);
    }

    public void intake(SampleMecanumDrive drive) {
        drive.followTrajectory(EnemyLeftTrajectories.IntakeTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(EnemyLeftTrajectories.IntakeTrajectory2(drive.getPoseEstimate()));
    }

    public void park(SampleMecanumDrive drive) {
        drive.followTrajectory(EnemyLeftTrajectories.ParkTrajectory(drive.getPoseEstimate()));
        drive.followTrajectory(EnemyLeftTrajectories.ParkTrajectory2(drive.getPoseEstimate()));
    }

}
