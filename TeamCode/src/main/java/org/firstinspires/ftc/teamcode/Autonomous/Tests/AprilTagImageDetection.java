package org.firstinspires.ftc.teamcode.Autonomous.Tests;

import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ImageDetection;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Hardware.HardwareUtils;
import org.firstinspires.ftc.teamcode.Utils.Sleep;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;

public class AprilTagImageDetection {
    public static OpenCvCamera camera;
    private static AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    static double fx = 578.272;
    static double fy = 578.272;
    static double cx = 402.145;
    static double cy = 221.506;

    // UNITS ARE METERS
    static double tagsize = 0.1;

    static int ID_TAG_OF_INTEREST_FIRST = 2;
    static int ID_TAG_OF_INTEREST_SECOND = 7;
    static int ID_TAG_OF_INTEREST_THIRD = 12;

    static AprilTagDetection tagOfInterest = null;
    static boolean tagseen = false;

    public enum BeaconCase{
        One,
        Two,
        Three
    }
    public static BeaconCase beaconCaseOut = BeaconCase.Three;

    public static void initialize() {
        int cameraMonitorViewId = HardwareUtils.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", HardwareUtils.hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(HardwareUtils.hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

    }

    public static void detect(){
        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

        if(currentDetections.size() != 0)
        {
            tagseen=true;

            for(AprilTagDetection tag : currentDetections)
            {
                if(tag.id == ID_TAG_OF_INTEREST_FIRST)
                {
                    beaconCaseOut=BeaconCase.One;
                    break;
                }
                if(tag.id == ID_TAG_OF_INTEREST_SECOND)
                {
                    beaconCaseOut=BeaconCase.Two;
                    break;
                }
                if(tag.id == ID_TAG_OF_INTEREST_THIRD)
                {
                    beaconCaseOut=BeaconCase.Three;
                    break;
                }
            }
            Hardware.telemetry.addData("BeaconCase", beaconCaseOut);
        }
        if (!tagseen)
        {
            Hardware.telemetry.addLine("Don't see tag of interest :(");
        }

        Hardware.telemetry.update();
        Sleep.ms(20);
    }

}
