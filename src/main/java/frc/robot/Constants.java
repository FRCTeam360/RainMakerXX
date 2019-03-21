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

    public static final double armF = 2.5575;
    public static final double armP = 68.736; //0.134251969, 0.26854, .537, 1.074, 2.148, 4.296, 8.592, 17.184, 34.368
    public static final double armI = 0.002; // .001, .002, .004, .008, .016, .012
    public static final double armD = 708; //687.36, 690, 700, 705, 703, 704, 705, 707

    public static final int armVel = 200;
    public static final int armAcc = 200;

    public static final double wristF = 1.023;
    public static final double wristP = 31.488; // .05115, .123, .246, .492, .984, 1.968, 3.936, 7.872, 15.744
    public static final double wristI = 0.001; // .001, .002
    public static final double wristD = 314.88;
    
    public static final int wristVel = 500;
    public static final int wristAcc = 500;


}
