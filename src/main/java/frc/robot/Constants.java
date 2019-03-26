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

    public static final double armF = 2.65714; // 2.65714
    public static final double armP = 36.0096; // 2.046, 4.096, 8.184, 16.368, 32.736
    public static final double armI = 0.001; //
    public static final double armD = 360.096; //

    public static final int armVel = 285;
    public static final int armAcc = 285;

    public static final double wristF = 1.023;
    public static final double wristP = 31.488; // .05115, .123, .246, .492, .984, 1.968, 3.936, 7.872, 15.744
    public static final double wristI = 0.001; // .001, .002
    public static final double wristD = 314.88;
    
    public static final int wristVel = 500;
    public static final int wristAcc = 500;

    public static final double armIntake = -100;
    public static final double armLow = -1100;
    public static final double armMid = -2030;
    public static final double armHigh = -3200;

    public static boolean panelPickUpActivation = false;
    public static final double armHatchOffset = 0;
    public static final double wristHatchOffset = 2000;


}
