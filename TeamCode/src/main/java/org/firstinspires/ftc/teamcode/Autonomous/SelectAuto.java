package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Left.AutoRunFiveConeLeft;
import org.firstinspires.ftc.teamcode.Autonomous.AutoRun.FiveCone.Right.AutoRunFiveConeRight;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PoseStorage;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.OneTap;

@Autonomous(name = "!!SELECT AUTO!!")
public class SelectAuto extends LinearOpMode {

    OneTap oneTapUp = new OneTap();
    OneTap oneTapDown = new OneTap();
    private final FtcDashboard ftcDashboard = FtcDashboard.getInstance();

    public void selectedCaseDisplay(AutoCase autoCase, TelemetryPacket telemetryPacket) {
        if (autoCase == PoseStorage.autoCase) {
            this.telemetry.addLine("*****" + PoseStorage.autoCase.name());
            telemetryPacket.addLine("*****" + PoseStorage.autoCase.name());
        } else {
            this.telemetry.addLine(autoCase.name());
            telemetryPacket.addLine(autoCase.name());
        }
    }

    public AutoCase getCaseFromOrdinal(int ordinal) {
        for (AutoCase autoCase : AutoCase.values()) {
            if (autoCase.ordinal() == ordinal) {
                return autoCase;
            }
        }
        return AutoCase.FiveConeRight;
    }

    public void changeCase() {
        int increment = 0;
        if (oneTapUp.onPress(this.gamepad1.dpad_up)) {
            increment = -1;
        } else if (oneTapDown.onPress(this.gamepad1.dpad_down)) {
            increment = 1;
        }
        PoseStorage.autoCase = getCaseFromOrdinal(
                ((PoseStorage.autoCase.ordinal() == 0 && increment == -1) || (PoseStorage.autoCase.ordinal() == AutoCase.values().length - 1 && increment == 1)) ? PoseStorage.autoCase.ordinal() :
                        PoseStorage.autoCase.ordinal() + increment);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addLine("Select autonomous program using gamepad 1 DPAD_UP and DPAD_DOWN");
        telemetry.update();
        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {

            changeCase();

            TelemetryPacket telemetryPacket = new TelemetryPacket();
            for (AutoCase autoCase : AutoCase.values()) {
                selectedCaseDisplay(autoCase, telemetryPacket);
            }
//            selectedState(telemetryPacket);
            telemetry.update();
            ftcDashboard.sendTelemetryPacket(telemetryPacket);
        }
    }

    public void selectedState(TelemetryPacket telemetryPacket) {

    }

    public static Runnable getAutoFromEnum(SampleMecanumDrive sampleMecanumDrive, LinearOpMode opMode) {
        if (PoseStorage.autoCase == AutoCase.FiveConeRight) {
            return new AutoRunFiveConeRight(sampleMecanumDrive, opMode);
        } else if (PoseStorage.autoCase == AutoCase.FiveConeLeft) {
            return new AutoRunFiveConeLeft(sampleMecanumDrive, opMode);
        } else {
            return new AutoRunFiveConeRight(sampleMecanumDrive, opMode);
        }
    }
}
