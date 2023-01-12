package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.TenCone.Right;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

public class TenConeRightTrajectories {

    public static void InitTrajectories() {

    }

    private static SampleMecanumDrive drive;

    public static void setDrive(SampleMecanumDrive Drive) {
        drive = Drive;
    }

    public static Trajectory IntakeTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d, true)
                .lineToLinearHeading(new Pose2d(35.31, -13, Math.toRadians(270)),
                        SampleMecanumDrive.getVelocityConstraint(80, Math.toRadians(15), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(45))
                .build();
    }

    public static Trajectory IntakeTrajectory2(Pose2d pose2d) {
        return drive.trajectoryBuilder(new Pose2d(pose2d.getX(), pose2d.getY(), 0))
                .lineToLinearHeading(new Pose2d(51.9, -11.5, Math.toRadians(4)),
                        SampleMecanumDrive.getVelocityConstraint(80, Math.toRadians(15), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(50))
                .build();
    }

    public static Trajectory ParkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(new Pose2d(pose2d.getX(), pose2d.getY(), Math.toRadians(270)))
                .lineToLinearHeading(AutoRunTenConeRight.detectedCase.getParkPoseRight(),
                        SampleMecanumDrive.getVelocityConstraint(90, Math.toRadians(50), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(70))
                .build();
    }
    public static Trajectory ParkTrajectory2(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .forward(10,
                        SampleMecanumDrive.getVelocityConstraint(90, Math.toRadians(50), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(70))
                .build();
        ///multa muie conacule
    }

    public static Trajectory GoToSecondStack(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d,true)
                .lineToLinearHeading(new Pose2d(-29, pose2d.getY()-0.5, Math.toRadians(-5)),
                    SampleMecanumDrive.getVelocityConstraint(70, Math.toRadians(15), DriveConstants.TRACK_WIDTH),
                    SampleMecanumDrive.getAccelerationConstraint(45))
                .build();
    }
    public static Trajectory GoToSecondStack2(Pose2d pose2d) {
        return drive.trajectoryBuilder(new Pose2d(pose2d.getX(), pose2d.getY(), Math.toRadians(180)))
                .lineToLinearHeading(new Pose2d(-51.25, -13.5, Math.toRadians(178)),
                        SampleMecanumDrive.getVelocityConstraint(50, Math.toRadians(15), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(30))
                .build();
    }
}
