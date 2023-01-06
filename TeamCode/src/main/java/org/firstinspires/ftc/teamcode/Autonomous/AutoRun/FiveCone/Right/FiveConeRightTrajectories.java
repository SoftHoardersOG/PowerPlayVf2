package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Right;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.robotcore.internal.android.dx.command.Main;
import org.firstinspires.ftc.teamcode.Autonomous.MainAuto;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PoseStorage;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

import java.util.ArrayList;

public class FiveConeRightTrajectories {

    public static void InitTrajectories() {

    }

    private static SampleMecanumDrive drive;

    public static void setDrive(SampleMecanumDrive Drive) {
        drive = Drive;
    }

    public static Trajectory IntakeTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d, true)
                .lineToLinearHeading(new Pose2d(35.31, -13, Math.toRadians(270)),
                        SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(10), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(30))
                .build();
    }

    public static Trajectory IntakeTrajectory2(Pose2d pose2d) {
        return drive.trajectoryBuilder(new Pose2d(pose2d.getX(), pose2d.getY(), 0))
                .lineToLinearHeading(new Pose2d(51.5, -11.5, Math.toRadians(4)),
                        SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(10), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(30))
                .build();
    }

    public static Trajectory ParkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(new Pose2d(pose2d.getX(), pose2d.getY(), 270))
                .lineToLinearHeading(AutoRunFiveConeRight.detectedCase.getParkPoseRight(),
                        SampleMecanumDrive.getVelocityConstraint(50, Math.toRadians(50), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(50))
                .build();
    }
    public static Trajectory ParkTrajectory2(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .forward(10,
                        SampleMecanumDrive.getVelocityConstraint(50, Math.toRadians(50), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(50))
                .build();
    }
}
