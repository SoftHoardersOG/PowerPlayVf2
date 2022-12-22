package org.firstinspires.ftc.teamcode.Mechanisms;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

import java.util.ArrayList;
import java.util.Arrays;

public class Intake {
    private static final Claw claw = new Claw(Hardware.frontClaw, 0.05, 0.22);
    private static final Arm arm = new Arm(Hardware.frontClawAngle, 0.11, 0.71, 0.15,0);
    private static final Position lift = new Position(Hardware.frontClawLift , new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0)));
    private static final frontSlides slide = new frontSlides();

    public static void open() {claw.open();}

    public static void close() {claw.close();}

    public static void transfer(){arm.transfer();}

    public static void goToMax(){slide.goToMax();}

    public static void neutralSlides(){slide.neutralSlides();}

    public static void collect(){arm.collectOrPlace();}

    public static void idle(){arm.idle();}

    public static void liftToPosition(int index){lift.goToPosition(index);}
}
