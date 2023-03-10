package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoCases;

public class B extends AutoCases {


    @Override
    public Pose2d getParkPoseRight() {
        return new Pose2d(34, -13, Math.toRadians(275));
    }

    @Override
    public Pose2d getParkPoseLeft() {
        return new Pose2d(-34, -13, Math.toRadians(260));
    }

    @Override
    public Pose2d getParkPoseTenRight() {
        return new Pose2d(-34, -13, Math.toRadians(105));
    }

    @Override
    public Pose2d getParkPoseTenLeft() {
        return new Pose2d(34, -13, Math.toRadians(15));
    }
}
