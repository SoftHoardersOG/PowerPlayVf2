package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Left;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;


import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

public class FiveConeLeftTrajectories {


    public static void InitTrajectories() {

    }

    private static SampleMecanumDrive drive;

    public static void setDrive(SampleMecanumDrive Drive) {
        drive = Drive;
    }

    public static Trajectory IntakeTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d, true)
                .lineToLinearHeading(new Pose2d(-35.31, -13, Math.toRadians(270)),
                        SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(10), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(30))
                .build();
    }

    public static Trajectory IntakeTrajectory2(Pose2d pose2d) {
        return drive.trajectoryBuilder(new Pose2d(pose2d.getX(), pose2d.getY(), 180))
                .lineToLinearHeading(new Pose2d(-41, -11.7, Math.toRadians(180)),//45
                        SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(70), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(30))
                .build();
    }

    public static Trajectory ParkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToLinearHeading(AutoRunFiveConeLeft.detectedCase.getParkPoseLeft(),
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
