package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.teamcode.Hardware.Hardware.telemetry;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

public class TelemetryManager {
    public static void manage(){
        telemetry.addData("Slide current position",Hardware.backSlide.getCurrentPosition());
        telemetry.update();
    }
}