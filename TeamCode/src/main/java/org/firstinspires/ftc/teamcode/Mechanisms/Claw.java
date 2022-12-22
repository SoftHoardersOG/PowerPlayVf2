package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    private final double closed;
    private final double opened;
    private final Servo servo;

    public Claw(Servo servo, double opened, double closed) {
        this.closed = closed;
        this.opened = opened;
        this.servo = servo;
    }

    public void open() {servo.setPosition(opened);}

    public void close() {servo.setPosition(closed);}
}
