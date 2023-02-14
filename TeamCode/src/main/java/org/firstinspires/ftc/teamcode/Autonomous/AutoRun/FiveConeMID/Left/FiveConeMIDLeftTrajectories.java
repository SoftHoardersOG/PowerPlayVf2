package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.PhantomCase;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

public class FiveConeLefttrajectoriesDefense {


    public static void InitTrajectories() {

    }

    private static SampleMecanumDrive drive;

    public static void setDrive(SampleMecanumDrive Drive) {
        drive = Drive;
    }

    public static Trajectory IntakeTrajectory1(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d, true)
                .lineToLinearHeading(new Pose2d(-35.31, -13, Math.toRadians(270)),
                        SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(10), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(30))
                .build();
    }
}
