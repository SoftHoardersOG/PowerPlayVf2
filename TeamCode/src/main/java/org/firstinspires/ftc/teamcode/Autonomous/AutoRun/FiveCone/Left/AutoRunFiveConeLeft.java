package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Left;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.A;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Right.FiveConeRightTrajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoCases;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;


public class AutoRunFiveConeLeft implements Runnable {

    private SampleMecanumDrive sampleMecanumDrive;
    AutoCases detectedCase;
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
//        switch (ImageDetection.duckPosition) {
//            case Left:
//                detectedCase = new A();
//                PoseStorageTeleOp.TMPosition = 1;
//                break;
//            case Middle:
//                detectedCase = new B();
//                PoseStorageTeleOp.TMPosition = 2;
//                break;
//            case Right:
//                detectedCase = new C();
//                PoseStorageTeleOp.TMPosition = 3;
//                break;
//        }
        detectedCase=new A();

//        ImageDetection.camera.stopStreaming();
        intake(sampleMecanumDrive);
    }

    public void intake(SampleMecanumDrive drive) {
        drive.followTrajectory(FiveConeLeftTrajectories.IntakeTrajectory(drive.getPoseEstimate()));
    }

}
