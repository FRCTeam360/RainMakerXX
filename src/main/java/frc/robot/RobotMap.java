/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.*;

public class RobotMap {
  public static PowerDistributionPanel pdp = new PowerDistributionPanel();

  public static TalonSRX armMotor = new TalonSRX(5);
  public static TalonSRX wristMotor = new TalonSRX(6);
  public static TalonSRX intakeMotor = new TalonSRX(7);

  public static DoubleSolenoid hatchPanel = new DoubleSolenoid(2, 3);

}
