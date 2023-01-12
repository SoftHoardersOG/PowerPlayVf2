package org.firstinspires.ftc.teamcode.Autonomous.AutoRun.TenCone.Left;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

public class TenConeLeftTrajectories {


    public static void InitTrajectories() {

    }

    private static SampleMecanumDrive drive;

    public static void setDrive(SampleMecanumDrive Drive) {
        drive = Drive;
    }

    public static Trajectory IntakeTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .build();
    }
}
