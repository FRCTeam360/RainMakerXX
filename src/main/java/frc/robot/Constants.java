/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Constants {
    public static final double highFactor = 22.66667;
    public static final double lowFactor = 7.56667;

    public static final int highShiftPoint = 5200;
    public static final int lowShiftPoint = 1000;

    public static final int encoderTics = 62;

    public static final int wristResetPosition = 3500;
    public static final int armIntakePosition = 0;

    public static final int armOffset = 90;

    public static final int armLowPosition = 1075 + armOffset;
    public static final int armMidPosition = 2060 + armOffset;
    public static final int armHighPosition = 3135 + armOffset;

    public static final int wristPanelPickUp = -3344;

    public static final double wristSpeed = .3;

    public static final double armAutoSpeedUp = -.55;
    public static final double armAutoSpeedDown = 0;
    public static final double armStaySpeed = -.2;

    public static final int armAutoThreshold = 100;

    public static final int wristThreshold = 100;

    public static boolean isInAutoShift = false;
    
    public static boolean armPanelPickUpActivation = false;
}
