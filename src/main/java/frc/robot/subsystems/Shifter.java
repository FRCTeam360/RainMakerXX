/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.commands.AutoShift;
import frc.robot.RobotMap;
import frc.robot.RobotMap.ShiftState;

public class Shifter extends Subsystem {

	private DoubleSolenoid shifter = RobotMap.shifter;
	
	public void shiftUp(){ 

		shifter.set(DoubleSolenoid.Value.kForward);
		RobotMap.shiftState = ShiftState.UP;
	}
	public void shiftDown() {
		
		shifter.set(DoubleSolenoid.Value.kReverse);
		RobotMap.shiftState = ShiftState.DOWN;
	}
  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new AutoShift());
  }
}
