/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;


public class RobotMap {
  public static PowerDistributionPanel pdp = new PowerDistributionPanel();

  public static DoubleSolenoid shifter = new DoubleSolenoid(2, 3);

  public static enum ShiftState {UP, DOWN, UNKNOWN};
  public static ShiftState shiftState = ShiftState.UNKNOWN;
  
  public static Compressor compressor = new Compressor(1);

  // public static TalonSRX motorLeftMaster = new TalonSRX(0);
	// public static TalonSRX motorLeftSlave = new TalonSRX(1);
	// public static TalonSRX motorRightMaster = new TalonSRX(2);
  // public static TalonSRX motorRightSlave = new TalonSRX(3);

  //public static CANSparkMax left1Motor = new CANSparkMax(0, MotorType.kBrushless);
  //public static CANSparkMax left2Motor = new CANSparkMax(1, MotorType.kBrushless);
  //public static CANSparkMax right1Motor = new CANSparkMax(2, MotorType.kBrushless);
  //public static CANSparkMax right2Motor = new CANSparkMax(3, MotorType.kBrushless);
  
  //public static Victor lift1Motor = new Victor(4);
  //public static Victor lift2Motor = new Victor(5);

  public static DigitalInput limitTop1 = new DigitalInput(6);
  public static DigitalInput limitTop2 = new DigitalInput(7);
  public static DigitalInput limitBottom1 = new DigitalInput(8);
  public static DigitalInput limitBottom2 = new DigitalInput(9);
  public static DigitalInput limitFront1 = new DigitalInput(10);
  public static DigitalInput limitFront2 = new DigitalInput(11);
  public static DigitalInput limitBack1 = new DigitalInput(12);
  public static DigitalInput limitBack2 = new DigitalInput(13);
  public static BuiltInAccelerometer accell = new BuiltInAccelerometer();

}
