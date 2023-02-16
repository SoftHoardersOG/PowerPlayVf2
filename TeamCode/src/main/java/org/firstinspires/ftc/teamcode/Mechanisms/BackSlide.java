package org.firstinspires.ftc.teamcode.Mechanisms;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

public class BackSlide {
    private final int lowPosition = 0;
    private final int midPosition = -225;
    private final int highPosition = -560;

    public void goToHigh(){Hardware.backSlide.setTargetPosition(highPosition);}
    public void goToHighAuto(){Hardware.backSlide.setTargetPosition(-525);}
    public void goToHighAutoRight(){Hardware.backSlide.setTargetPosition(-535);}
    public void goToHighAutoLeft(){Hardware.backSlide.setTargetPosition(-465);}

    public void goToMid(){Hardware.backSlide.setTargetPosition(midPosition);}

    public void goToLow(){Hardware.backSlide.setTargetPosition(lowPosition);}
}
