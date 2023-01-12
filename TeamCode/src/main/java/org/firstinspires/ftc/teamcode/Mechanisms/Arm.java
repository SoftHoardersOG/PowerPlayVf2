package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private final double transfer;
    private final double collectOrPlace;
    private final double idle;
    private final double finalRelease;
    private final Servo servo;

    public Arm(Servo servo, double transfer, double collect, double idle,double release) {
        this.transfer = transfer;
        this.collectOrPlace = collect;
        this.idle = idle;
        this.servo = servo;
        this.finalRelease = release;
    }

    public void finalRelease(){servo.setPosition(finalRelease);}

    public void transfer() {servo.setPosition(transfer);}

    public void collectOrPlace() {servo.setPosition(collectOrPlace);}

    public void idle() {servo.setPosition(idle);
    }
}
