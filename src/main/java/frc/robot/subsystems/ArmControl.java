/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ControlArm;

/**
 * Add your docs here.
 */
public class ArmControl extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private static TalonSRX armMotor = RobotMap.armMotor;

  public void moveArm (double speed){
    armMotor.set(ControlMode.PercentOutput, speed);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ControlArm());
  }

 
  
  
}
