package org.firstinspires.ftc.teamcode.RoadRunner.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "drive")
public class SplineTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Hardware.modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        Hardware. gyro = (IntegratingGyroscope)Hardware.modernRoboticsI2cGyro;
        // If you're only interested int the IntegratingGyroscope interface, the following will suffice.
        // gyro = hardwareMap.get(IntegratingGyroscope.class, "gyro");
        // A similar approach will work for the Gyroscope interface, if that's all you need.

        // Start calibrating the gyro. This takes a few seconds and is worth performing
        // during the initialization phase at the start of each OpMode.
        telemetry.log().add("Gyro Calibrating. Do Not Move!");
        Hardware.modernRoboticsI2cGyro.calibrate();

        // Wait until the gyro calibration is complete
        while (!isStopRequested() && Hardware.modernRoboticsI2cGyro.isCalibrating())  {
            telemetry.addData("calibrating", "");
            telemetry.update();
            sleep(50);
        }

        telemetry.log().clear(); telemetry.log().add("Gyro Calibrated. Press Start.");
        telemetry.clear(); telemetry.update();


        waitForStart();

        if (isStopRequested()) return;

        Trajectory traj = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(30, 30), 0,
                        SampleMecanumDrive.getVelocityConstraint(70, Math.toRadians(90), DriveConstants.TRACK_WIDTH),
                        SampleMecanumDrive.getAccelerationConstraint(50))
                .build();

        drive.followTrajectory(traj);

        sleep(2000);

        drive.followTrajectory(
                drive.trajectoryBuilder(traj.end(), true)
                        .splineTo(new Vector2d(0, 0), Math.toRadians(180),
                                SampleMecanumDrive.getVelocityConstraint(70, Math.toRadians(90), DriveConstants.TRACK_WIDTH),
                                SampleMecanumDrive.getAccelerationConstraint(50))
                        .build()
        );
    }
}
