/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ArmManual;

/**
 * Add your docs here.
 */
public class ArmControl extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void articulateArm(double armMotor){
    RobotMap.armMotor.setNeutralMode(NeutralMode.Brake);
    RobotMap.armMotor.set(ControlMode.PercentOutput, armMotor);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ArmManual());
  }
}
