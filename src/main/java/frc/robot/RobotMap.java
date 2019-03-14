/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static PowerDistributionPanel pdp = new PowerDistributionPanel();

  public static TalonSRX liftMotorR = new TalonSRX(10);
  public static TalonSRX liftMotorL = new TalonSRX(11);
  public static TalonSRX climbM1 = new TalonSRX(9);
  public static TalonSRX climbM2 = new TalonSRX(8);
}
