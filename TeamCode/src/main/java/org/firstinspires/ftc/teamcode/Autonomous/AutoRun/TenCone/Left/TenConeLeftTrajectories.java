package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.TenCone.Left;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Left.AutoRunFiveConeLeft;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.TenCone.Right.AutoRunTenConeRight;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

public class TenConeLeftTrajectories {
    public static void InitTrajectories() {

    }

    private static SampleMecanumDrive drive;

    public static void setDrive(SampleMecanumDrive Drive) {
        drive = Drive;
    }

    public static Trajectory IntakeTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d, true)
                .lineToLinearHeading(new Pose2d(-35.31, -13, Math.toRadians(270)),
                        SampleMecanumDrive.getVelocityConstraint(80, Math.toRadians(15), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(45)) ///45
                .build();
    }

    public static Trajectory IntakeTrajectory2(Pose2d pose2d) {
        return drive.trajectoryBuilder(new Pose2d(pose2d.getX(), pose2d.getY(), 180))
                .lineToLinearHeading(new Pose2d(-52.5, -11.5, Math.toRadians(176)),
                        SampleMecanumDrive.getVelocityConstraint(100, Math.toRadians(140), DriveConstants.TRACK_WIDTH), ///80->100
                        SampleMecanumDrive.getAccelerationConstraint(50)) ////50
                .build();
    }

    public static Trajectory ParkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToLinearHeading(AutoRunTenConeLeft.detectedCase.getParkPoseTenLeft(),
                        SampleMecanumDrive.getVelocityConstraint(50, Math.toRadians(200), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(50)) ///50
                .build();
    }

    public static Trajectory ParkTrajectory2(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .forward(10,
                        SampleMecanumDrive.getVelocityConstraint(50, Math.toRadians(50), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(50)) ///50
                .build();
    }

    public static Trajectory GoToSecondStack(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d,true)
                .lineToLinearHeading(new Pose2d(29, pose2d.getY()-0.5, Math.toRadians(180)),
                        SampleMecanumDrive.getVelocityConstraint(70, Math.toRadians(15), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(45)) //45
                .build();
    }
    public static Trajectory GoToSecondStack2(Pose2d pose2d) {
        return drive.trajectoryBuilder(new Pose2d(pose2d.getX(), pose2d.getY(), Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(51.5, -13.5, Math.toRadians(2)),
                        SampleMecanumDrive.getVelocityConstraint(50, Math.toRadians(150), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(30)) //30
                .build();
    }
}
