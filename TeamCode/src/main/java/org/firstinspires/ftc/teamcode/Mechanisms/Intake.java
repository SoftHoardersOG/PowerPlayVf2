package org.firstinspires.ftc.teamcode.Mechanisms;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

import java.util.ArrayList;
import java.util.Arrays;

public class Intake {
    private static final Claw claw = new Claw(Hardware.frontClaw, 0.05, 0.22);
    private static final Arm arm = new Arm(Hardware.frontClawAngle, 0.11, 0.71, 0.15,0.67);
    private static final Position lift = new Position(Hardware.frontClawLift , new ArrayList<Double>(Arrays.asList(0.06, 0.15, 0.25, 0.37, 0.4, 0.08)));
    private static final FrontSlides slide = new FrontSlides();
    public static int currentPosition=0;

    public static void open() {claw.open();}

    public static void close() {claw.close();}

    public static void transfer(){arm.transfer();}

    public static void goToMax(){slide.goToMax();}

    public static void neutralSlides(){slide.neutralSlides();}

    public static void collect(){arm.collectOrPlace();}

    public static void idle(){arm.idle();}

    public static void liftToPosition(int index){lift.goToPosition(index);}

    public static void changeLiftToPosition(int changeIndex){
        currentPosition+=changeIndex;
        if (currentPosition<0){
            currentPosition=0;
        }
        if (currentPosition>4){
            currentPosition=4;
        }
        if (currentPosition==4){
            arm.finalRelease();
        }
        lift.goToPosition(currentPosition);
    }
}
