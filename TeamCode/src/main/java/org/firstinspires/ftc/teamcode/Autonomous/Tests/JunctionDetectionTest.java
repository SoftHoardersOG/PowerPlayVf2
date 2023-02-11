package org.firstinspires.ftc.teamcode.Autonomous.Tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Hardware.HardwareUtils;
import org.firstinspires.ftc.teamcode.Mechanisms.Claw;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;


public class JunctionDetectionTest {
    public static OpenCvCamera camera;

    public static void initialize(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 2");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        camera.setPipeline(new JunctionDetectionTest.Pipeline());

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                FtcDashboard.getInstance().startCameraStream(camera,60);
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });

    }

    static class Pipeline extends OpenCvPipeline {


        Mat crMat = new Mat();
        Mat yCr = new Mat();
        Mat filtered = new Mat();


        @Override
        public void init(Mat firstFrame) {
        }

        @Override
        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input, yCr, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(yCr, crMat, 2);
            //Core.inRange(crMat, new Scalar(0, 30, 0), new Scalar(255, 255, 255), filtered);
            return crMat;
        }

    }
}
