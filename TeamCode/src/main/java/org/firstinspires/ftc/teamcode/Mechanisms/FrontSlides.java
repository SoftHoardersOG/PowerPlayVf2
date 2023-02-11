package org.firstinspires.ftc.teamcode.Mechanisms;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;


public class FrontSlides {

    private final int maxPositionRight =-700;
    private final int maxPositionLeft =700;
    private final int neutralPosition=0;

    public void goToMax(){
        Hardware.rightSlide.setTargetPosition(maxPositionRight);
        Hardware.leftSlide.setTargetPosition(maxPositionLeft);
    }
    public void neutralSlides(){
        Hardware.rightSlide.setTargetPosition(neutralPosition);
        Hardware.leftSlide.setTargetPosition(neutralPosition);
    }

    public boolean isExtended(){
        return Hardware.leftSlide.getCurrentPosition()>maxPositionLeft-200||Hardware.rightSlide.getCurrentPosition()<maxPositionRight+200;
    }

}
