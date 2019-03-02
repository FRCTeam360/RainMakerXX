/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static TalonSRX climbM1 = new TalonSRX(13);
  public static TalonSRX climbM2 = new TalonSRX(12);
  public static PowerDistributionPanel pdp = new PowerDistributionPanel();

  public static DoubleSolenoid shifter = new DoubleSolenoid(0, 1);

  public static DoubleSolenoid hatchPanel = new DoubleSolenoid(2, 3);

  public static int currentPos;
  public static int wristOffset = 0;

  public static int currentArmPos;

  public static double goalWristPos = 0;
  public static boolean shouldWristStop = false;

  public static double goalArmPos = 0;
  public static boolean shouldArmStop = false;

  public static enum ShiftState {UP, DOWN, UNKNOWN};
  public static ShiftState shiftState = ShiftState.UNKNOWN;
  
  public static Compressor compressor = new Compressor();

  public static CANSparkMax left1Motor = new CANSparkMax(1, MotorType.kBrushless);
  public static CANSparkMax left2Motor = new CANSparkMax(2, MotorType.kBrushless);
  public static CANSparkMax right1Motor = new CANSparkMax(3, MotorType.kBrushless);
  public static CANSparkMax right2Motor = new CANSparkMax(4, MotorType.kBrushless);

  public static TalonSRX armMotor = new TalonSRX(5);
  public static TalonSRX wristMotor = new TalonSRX(6);
  public static TalonSRX intakeMotor = new TalonSRX(7);

  public static DigitalInput armSwitch = new DigitalInput(1);
  
}
