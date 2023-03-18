package org.firstinspires.ftc.teamcode.Mechanisms;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;
import org.firstinspires.ftc.teamcode.TeleOp.ActionManager;

import java.util.ArrayList;
import java.util.Arrays;

public class Place {
    private static final Claw claw = new Claw(Hardware.backClaw, 0.77, 0.69);
    private static final Arm arm = new Arm(Hardware.backClawAngle, 0.96, 0.33, 0.23,0.27);
    private static final Position turret = new Position(Hardware.turret, new ArrayList<Double>(Arrays.asList(0.00, 0.97, 0.6, 0.48, 0.25, 0.84)));
    private static final BackSlide slide = new BackSlide();

    public static void close(){claw.close();}

    public static void open(){claw.open();}

    public static void transfer(){arm.transfer();}

    public static void lowJunkPoz(){arm.idle();}

    public static void place(){arm.collectOrPlace();}

    public static void release(){arm.finalRelease();}

    public static void turretToPosition(int index){
        if (!ActionManager.cycling){
            if (index==0){
                index=4;
            }
            if (index==1){
                index=5;
            }
        }
        turret.goToPosition(index);
    }

    public static void low(){slide.goToLow();}

    public static void mid(){slide.goToMid();}

    public static void high(){slide.goToHigh();}
    public static void highAuto(){slide.goToHighAuto();}
    public static void highAutoLeft(){slide.goToHighAutoLeft();}
    public static void highAutoLeftPreload(){slide.goToHighAutoLeftPreload();}
    public static void highAutoRight(){slide.goToHighAutoRight();}

}
