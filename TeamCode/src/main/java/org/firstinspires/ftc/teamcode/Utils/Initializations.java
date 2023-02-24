package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOp.Movement;
import org.firstinspires.ftc.teamcode.TeleOp.TelemetryManager;

public class Initializations {
    public static void initTeleOp(HardwareMap hardwareMap, SampleMecanumDrive drive, Telemetry telemetry){
        Hardware.init(hardwareMap, telemetry);
        Movement.init(drive);
        Hardware.configureTeleOp();
        TelemetryManager.init();
        Rumble.init();
        Gyro.calibrate();
    }
    public static void initAuto(HardwareMap hardwareMap, Telemetry telemetry){
        Hardware.init(hardwareMap, telemetry);
        Hardware.configureAuto();
        TelemetryManager.init();
    }
}
