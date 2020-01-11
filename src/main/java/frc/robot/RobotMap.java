/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class RobotMap {
  public static PowerDistributionPanel pdp = new PowerDistributionPanel();

  public static DoubleSolenoid shifter = new DoubleSolenoid(3, 2);

  //Color sensor threshold data - DONT TOUCH VALUES
  //public static boolean[] colorSensorData = {false, false, false};

  public static enum ShiftState {UP, DOWN, UNKNOWN}
  public static ShiftState shiftState = ShiftState.UNKNOWN;
  
  public static Compressor compressor = new Compressor();

  public static TalonSRX motorLeftMaster = new TalonSRX(0);
	public static TalonSRX motorLeftSlave = new TalonSRX(1);
	public static TalonSRX motorRightMaster = new TalonSRX(2);
  public static TalonSRX motorRightSlave = new TalonSRX(3);
  
  // public static TalonSRX motor = new TalonSRX(2);
}
