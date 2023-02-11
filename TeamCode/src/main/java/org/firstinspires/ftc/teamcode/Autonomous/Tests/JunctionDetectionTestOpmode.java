package org.firstinspires.ftc.teamcode.Autonomous.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(group = "test", name = "junction detection test")
public class JunctionDetectionTestOpmode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        JunctionDetectionTest.initialize(hardwareMap);

        waitForStart();
        while (!isStopRequested() && opModeIsActive());
        JunctionDetectionTest.camera.stopStreaming();
    }
}
