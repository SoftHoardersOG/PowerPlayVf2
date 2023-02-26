package org.firstinspires.ftc.teamcode.RoadRunner.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

/**
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */
@TeleOp(group = "drive")
public class LocalizationTest extends LinearOpMode {

    // A timer helps provide feedback while calibration is taking place
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

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
        timer.reset();
        while (!isStopRequested() && Hardware.modernRoboticsI2cGyro.isCalibrating())  {
            telemetry.addData("calibrating", "%s", Math.round(timer.seconds())%2==0 ? "|.." : "..|");
            telemetry.update();
            sleep(50);
        }

        telemetry.log().clear(); telemetry.log().add("Gyro Calibrated. Press Start.");
        telemetry.clear(); telemetry.update();

        waitForStart();

        while (!isStopRequested()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();

            telemetry.addData("encoder front right", hardwareMap.get(DcMotorEx.class, "frontRight").getCurrentPosition());
            telemetry.addData("encoder back right", hardwareMap.get(DcMotorEx.class, "backRight").getCurrentPosition());
            telemetry.addData("encoder front left", hardwareMap.get(DcMotorEx.class, "frontLeft").getCurrentPosition());
            telemetry.addData("encoder back left", hardwareMap.get(DcMotorEx.class, "backLeft").getCurrentPosition());
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
        }
    }
}
