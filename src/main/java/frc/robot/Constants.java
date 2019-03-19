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

    public static final int wristResetPosition = 3500;
    public static final int wristBallPosition = -950;

    public static final int armIntakePosition = 0;

    public static int armHatchPanelOffset = 200;

    public static final int armLowPosition = -400;
    public static final int armMidPosition = -2000;
    public static final int armHighPosition = -2400;

    public static final int wristPanelPickUp = 2700;

    public static final double wristSpeed = .2;

    public static final double armAutoSpeedUp = .85;
    public static final double armAutoSpeedDown = -.25;
    public static final double armStaySpeed = .3;

    public static final int armAutoThreshold = 350;
    public static final int wristThreshold = 150;

    public static boolean defenseActivation = false;

    public static final double wristF = .93;
    public static final double wristP = 0.4;
    public static final double wristI = 0.002;
    public static final double wristD = 0.025;

    public static final double armF = 2.5575;
    public static final double armP = 47; //46.7657, oscilates at 93.5314, 70.1486
    public static final double armI = 0;
    public static final double armD = 0;

    // public static final double armI = .004;

    public static boolean isInAutoShift = false;

    public static boolean isClimbing = false;

    public static boolean hasCargo = false;
    
    public static boolean armPanelPickUpActivation = false;
}
