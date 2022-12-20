package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;

public class Position {
    private final ArrayList<Double> list;
    private final Servo servo;

    public Position(Servo servo, ArrayList<Double> list) {
        this.list = list;
        this.servo = servo;
    }

    public void goToPosition(int index){
        if (index>=list.size()){
            index=list.size()-1;
        }
        if (index<0){
            index=0;
        }
        this.servo.setPosition(list.get(index));
    }

}
