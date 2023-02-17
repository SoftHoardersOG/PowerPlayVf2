package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveConeMID.Right;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Right.AutoRunFiveConeRight;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

public class FiveConeMIDRightTrajectories {
    public static void InitTrajectories() {

    }

    private static SampleMecanumDrive drive;

    public static void setDrive(SampleMecanumDrive Drive) {
        drive = Drive;
    }

    public static Trajectory IntakeTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d, true)
                .lineToLinearHeading(new Pose2d(6, -60.30, Math.toRadians(270.00)),
                        SampleMecanumDrive.getVelocityConstraint(50, Math.toRadians(10), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(50))
                .build();
    }

    public static Trajectory IntakeTrajectory2(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToLinearHeading(new Pose2d(9, -12, Math.toRadians(270.00)),
                        SampleMecanumDrive.getVelocityConstraint(50, Math.toRadians(10), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(50))
                .build();
    }

    public static Trajectory IntakeTrajectory3(Pose2d pose2d) {
        return drive.trajectoryBuilder(new Pose2d(pose2d.getX(), pose2d.getY(), 0))
                .lineToLinearHeading(new Pose2d(17, -13, Math.toRadians(6)),
                        SampleMecanumDrive.getVelocityConstraint(50, Math.toRadians(30), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(50))
                .build();
    }

    public static Trajectory Repose(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToLinearHeading(new Pose2d(AutoRunFiveConeMIDRight.reposePose.getX(), AutoRunFiveConeMIDRight.reposePose.getY()+0.01, AutoRunFiveConeMIDRight.reposePose.getHeading()),
                        SampleMecanumDrive.getVelocityConstraint(50, Math.toRadians(30), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(50))
                .build();
    }

    public static Trajectory ParkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(new Pose2d(pose2d.getX(), pose2d.getY(), 270))
                .lineToLinearHeading(AutoRunFiveConeMIDRight.detectedCase.getParkPoseRight(),
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
