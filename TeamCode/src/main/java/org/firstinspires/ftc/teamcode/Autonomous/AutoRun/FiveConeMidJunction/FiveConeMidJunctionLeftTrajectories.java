package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveConeMidJunction;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;


import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Left.AutoRunFiveConeLeft;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

public class FiveConeMidJunctionLeftTrajectories {


    public static void InitTrajectories() {

    }

    private static SampleMecanumDrive drive;

    public static void setDrive(SampleMecanumDrive Drive) {
        drive = Drive;
    }

    public static Trajectory IntakeTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d, true)
                .lineToLinearHeading(new Pose2d(-35.31, -13, Math.toRadians(270)),
                        SampleMecanumDrive.getVelocityConstraint(50, Math.toRadians(40), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(30))
                .build();
    }

    public static Trajectory IntakeTrajectory2(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToLinearHeading(new Pose2d(-37, -16, Math.toRadians(182)),//45
                        SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(80), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(30))
                .build();
    }

    public static Trajectory ParkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToLinearHeading(AutoRunFiveConeMidJunctionLeft.detectedCase.getParkPoseLeft(),
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

    public static Trajectory Repose(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .forward(1)
                .addTemporalMarker(0, ()->{
                })
                .build();
    }
}
