package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Autonomous.Tests.AprilTagImageDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ImageDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PoseStorage;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Hardware.HardwareUtils;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.Initializations;

@Autonomous(name = "Main Auto", preselectTeleOp = "MainTeleOp")
public class MainAuto extends LinearOpMode {
    public static long firstTime;
    public static double duration = 0;
    public static boolean isAPressed = false;

    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive sampleMecanumDrive;
        sampleMecanumDrive = new SampleMecanumDrive(hardwareMap);

        Initializations.initAuto(hardwareMap, telemetry);

        AprilTagImageDetection.initialize();

        Thread linearAuto = new Thread(SelectAuto.getAutoFromEnum(sampleMecanumDrive, this));
        if (PoseStorage.autoCase.toString().contains("Right")){
            sampleMecanumDrive.setPoseEstimate(new Pose2d(35.75, -63.33, Math.toRadians(270)));
        }
        else{
            sampleMecanumDrive.setPoseEstimate(new Pose2d(-35.75, -63.33, Math.toRadians(270)));
        }

        while (!opModeIsActive() && !isStopRequested()){
            AprilTagImageDetection.detect();
        }
        linearAuto.start();
        firstTime = System.currentTimeMillis();

        while (!isStopRequested() && opModeIsActive()) {
            isAPressed = gamepad1.a;
            Hardware.telemetry.addData("backslide pose", Hardware.backSlide.getCurrentPosition());
            if (!linearAuto.isAlive()) {
                if (duration == 0) {
                    duration = (System.currentTimeMillis() - firstTime) / (1000.0);
                }
                Hardware.telemetry.addData("----AUTO LASTED----", duration);
            }
            Hardware.telemetry.update();
        }
        linearAuto.interrupt();
    }
}
