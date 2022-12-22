package org.firstinspires.ftc.teamcode.Mechanisms;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

import java.util.ArrayList;
import java.util.Arrays;

public class Place {
    private static final Claw claw = new Claw(Hardware.backClaw, 0.75, 0.70);
    private static final Arm arm = new Arm(Hardware.backClawAngle, 0.94, 0.23, 0,0.20);
    private static final Position turret = new Position(Hardware.turret, new ArrayList<Double>(Arrays.asList(0.88, 0.25, 0.60, 0.54)));
    private static final BackSlide slide = new BackSlide();

    public static void close(){claw.close();}

    public static void open(){claw.open();}

    public static void transfer(){arm.transfer();}

    public static void place(){arm.collectOrPlace();}

    public static void release(){arm.finalRelease();}

    public static void turretToPosition(int index){turret.goToPosition(index);}

    public static void low(){slide.goToLow();}

    public static void mid(){slide.goToMid();}

    public static void high(){slide.goToHigh();}

}
