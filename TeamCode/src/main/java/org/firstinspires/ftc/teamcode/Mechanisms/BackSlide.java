package org.firstinspires.ftc.teamcode.Mechanisms;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

public class BackSlide {
    private final int lowPosition = 0;
    private final int midPosition = -215;
    private final int highPosition = -515;

    public void goToHigh(){Hardware.backSlide.setTargetPosition(highPosition);}
    public void goToHighAuto(){Hardware.backSlide.setTargetPosition(highPosition-15);}

    public void goToMid(){Hardware.backSlide.setTargetPosition(midPosition);}

    public void goToLow(){Hardware.backSlide.setTargetPosition(lowPosition);}
}
