/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.*;

public class RobotMap {
  public static PowerDistributionPanel pdp = new PowerDistributionPanel();

  public static DoubleSolenoid shifter = new DoubleSolenoid(1, 0);

  public static enum ShiftState {UP, DOWN, UNKNOWN};
  public static ShiftState shiftState = ShiftState.UNKNOWN;
  
  public static Compressor compressor = new Compressor();

  public static CANSparkMax left1Motor = new CANSparkMax(1, MotorType.kBrushless);
  public static CANSparkMax left2Motor = new CANSparkMax(2, MotorType.kBrushless);
  public static CANSparkMax right1Motor = new CANSparkMax(3, MotorType.kBrushless);
  public static CANSparkMax right2Motor = new CANSparkMax(4, MotorType.kBrushless);
}
