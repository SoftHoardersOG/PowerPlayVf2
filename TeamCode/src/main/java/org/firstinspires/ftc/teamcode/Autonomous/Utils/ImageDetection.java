package org.firstinspires.ftc.teamcode.Autonomous.Utils;

import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Hardware.HardwareUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

public class ImageDetection {
    public static OpenCvCamera camera;
    public enum BeaconCase{
        One,
        Two,
        Three
    }
    public static BeaconCase beaconCaseOut;

    public static void initialize() {
        int cameraMonitorViewId = HardwareUtils.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", HardwareUtils.hardwareMap.appContext.getPackageName());
        WebcamName webcamName = HardwareUtils.hardwareMap.get(WebcamName.class, "Webcam 1");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        camera.setPipeline(new Pipeline());

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

    public static void updatePosition(BeaconCase position){
        beaconCaseOut = position;
    }

    static class Pipeline extends OpenCvPipeline {
        Point regionTopLeft = new Point(139,109);
        Point regionBottomRight = new Point(162,152);

        Mat submat;

        double meanGreen;
        double meanRed;
        double meanBlue;

        double maxMean;

        BeaconCase beaconCase;

        @Override
        public void init(Mat firstFrame) {
            submat=firstFrame.submat(new Rect(regionTopLeft, regionBottomRight));
        }

        @Override
        public Mat processFrame(Mat input) {

            Imgproc.rectangle(input, regionTopLeft, regionBottomRight, new Scalar(255, 255, 255), 2);

            meanGreen = Core.mean(submat).val[1];
            meanRed = Core.mean(submat).val[0];
            meanBlue = Core.mean(submat).val[2];

            maxMean = meanRed;
            beaconCase = BeaconCase.One;
            if (meanGreen > maxMean){
                maxMean = meanGreen;
                beaconCase = BeaconCase.Two;
            }
            if (meanBlue > maxMean){
                maxMean = meanBlue;
                beaconCase = BeaconCase.Three;
            }

            updatePosition(beaconCase);
            Hardware.telemetry.addData("Red Amount", meanRed);
            Hardware.telemetry.addData("Green Amount", meanGreen);
            Hardware.telemetry.addData("Blue Amount", meanBlue);
            Hardware.telemetry.addData("Detected Case: ", beaconCase);
            Hardware.telemetry.update();

            return input;
        }

    }
}
