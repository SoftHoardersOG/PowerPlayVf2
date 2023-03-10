package org.firstinspires.ftc.teamcode.RoadRunner.drive;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.RoadRunner.util.Encoder;

import java.util.Arrays;
import java.util.List;

/*
 * Sample tracking wheel localizer implementation assuming the standard configuration:
 *
 *    /--------------\
 *    |     ____     |
 *    |     ----     |
 *    | ||        || |
 *    | ||        || |
 *    |              |
 *    |              |
 *    \--------------/
 *
 */
@Config
public class Odometry extends ThreeTrackingWheelLocalizer {
    public static double TICKS_PER_REV = 8192;
    public static double WHEEL_RADIUS = 0.748031496; // in
    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed

    public static double LATERAL_DISTANCE = 5.276; // in; distance between the left and right wheels
    public static double FORWARD_OFFSET = -1; // in; offset of the lateral wheel

    private Encoder leftEncoder, rightEncoder, frontEncoder;

    public Odometry(HardwareMap hardwareMap) {
        super(Arrays.asList(
                new Pose2d(0, LATERAL_DISTANCE / 2, 0), // left
                new Pose2d(0, -LATERAL_DISTANCE / 2, 0), // right
                new Pose2d(FORWARD_OFFSET, 1.575, Math.toRadians(90)) // front 1.575
        ));

        DcMotorEx leftEncoderMotorPort = hardwareMap.get(DcMotorEx.class, "backRight");
        DcMotorEx rightEncoderMotorPort = hardwareMap.get(DcMotorEx.class, "frontRight");
        DcMotorEx frontEncoderMotorPort = hardwareMap.get(DcMotorEx.class, "backLeft");

        leftEncoder = new Encoder(leftEncoderMotorPort);
        rightEncoder = new Encoder(rightEncoderMotorPort);
        frontEncoder = new Encoder(frontEncoderMotorPort);

        int initialLeftEncoderPosition = leftEncoder.getCurrentPosition();
        int initialRightEncoderPosition = rightEncoder.getCurrentPosition();
        int initialFrontEncoderPosition = frontEncoder.getCurrentPosition();


        // TODO: reverse any encoders using Encoder.setDirection(Encoder.Direction.REVERSE)
    }

    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        return Arrays.asList(
                encoderTicksToInches(leftEncoder.getCurrentPosition()),
                encoderTicksToInches(-rightEncoder.getCurrentPosition()),
                encoderTicksToInches(frontEncoder.getCurrentPosition())
        );
    }

    @NonNull
    @Override
    public List<Double> getWheelVelocities() {
        // TODO: If your encoder velocity can exceed 32767 counts / second (such as the REV Through Bore and other
        //  competing magnetic encoders), change Encoder.getRawVelocity() to Encoder.getCorrectedVelocity() to enable a
        //  compensation method

        return Arrays.asList(
                encoderTicksToInches(leftEncoder.getCorrectedVelocity()),
                encoderTicksToInches(rightEncoder.getCorrectedVelocity()),
                encoderTicksToInches(frontEncoder.getCorrectedVelocity())
        );
    }
}
