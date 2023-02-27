package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.teamcode.Hardware.Hardware.telemetry;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;

public class TelemetryManager {

    private static FtcDashboard dashboard;
    private static TelemetryPacket packet;

    public static void init(){
        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);
    }


    public static void manage(){
        packet = new TelemetryPacket();
        addTelemetry("Slide current position", Hardware.backSlide.getCurrentPosition());
        addTelemetry("Slide current position",Hardware.backSlide.getCurrentPosition());
        addTelemetry("rightSlide position",Hardware.rightSlide.getCurrentPosition());
        addTelemetry("leftSlide position",Hardware.leftSlide.getCurrentPosition());
        addTelemetry("backsSlide current RPM", Hardware.backSlide.getVelocity(AngleUnit.DEGREES)/6);
        addTelemetry("front lift current positon", Intake.currentPosition);
        addTelemetry("color sensor reading: ", Hardware.sensor.getDistance(DistanceUnit.CM));
        addTelemetry("!!!!!!!!!!TRANSFER USING SENSOR!!!!!!!!!!!", !ActionManager.transfer);
        addTelemetry("potentiometer value: ", Hardware.potentiometer.getVoltage());
        addTelemetry("fronnt right: ", -Hardware.frontRight.getCurrentPosition());
        addTelemetry("back right: ", Hardware.backRight.getCurrentPosition());
        addTelemetry("back left: ", Hardware.backLeft.getCurrentPosition());
        addTelemetry("front left: ", Hardware.frontLeft.getCurrentPosition());
        addTelemetry("magnetic sensor pressed:", Hardware.magneticSensor.isPressed());
        addTelemetry("magnetic sensor value?:", Hardware.magneticSensor.getValue());
        dashboard.sendTelemetryPacket(packet);
        telemetry.update();
    }

    private static void addTelemetry(String caption, int value){
        telemetry.addData(caption, value);
        packet.put(caption, value);
    }

    private static void addTelemetry(String caption, double value){
        telemetry.addData(caption, value);
        packet.put(caption, value);
    }

    private static void addTelemetry(String caption, String value){
        telemetry.addData(caption, value);
        packet.put(caption, value);
    }

    private static void addTelemetry(String caption, boolean value){
        telemetry.addData(caption, value);
        packet.put(caption, value);
    }
}

