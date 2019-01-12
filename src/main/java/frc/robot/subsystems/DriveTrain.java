/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class DriveTrain extends Subsystem {

  // public TalonSRX motorRMaster = RobotMap.motorRightMaster;
	// private TalonSRX motorRSlave = RobotMap.motorRightSlave;
	
	// public TalonSRX motorLMaster = RobotMap.motorLeftMaster;
  // private TalonSRX motorLSlave = RobotMap.motorLeftSlave;
 
  public CANSparkMax motorR1 = RobotMap.right1Motor;
  public CANSparkMax motorR2 = RobotMap.right2Motor;
  public CANSparkMax motorL1 = RobotMap.left1Motor;
  public CANSparkMax motorL2 = RobotMap.left2Motor;

  public DriveTrain() {

    // motorRSlave.follow(motorRMaster);
    // motorLSlave.follow(motorLMaster);

    motorL2.follow(motorL1);
    motorR2.follow(motorR1);
    
    // motorLMaster.setInverted(false);
    // motorLSlave.setInverted(false);
		
		// motorRMaster.setInverted(true);
    // motorRSlave.setInverted(true);
    
    motorL1.setInverted(false);
    motorL2.setInverted(false);
    motorR1.setInverted(true);
    motorR2.setInverted(true);
		
		// motorLMaster.setSensorPhase(false);
    // motorRMaster.setSensorPhase(false);
    
    // motorLMaster.selectProfileSlot(0, 0);
		// motorRMaster.selectProfileSlot(0, 0);
		
		// resetTalons(motorRMaster);
		// resetTalons(motorLMaster);
		
		// resetTalons(motorRSlave);
    // resetTalons(motorLSlave);
  }
  // public void driveR(double RMotor) {
	// 	motorRMaster.set(ControlMode.PercentOutput, RMotor);
	// }
	// public void driveL(double LMotor){
	// 	motorLMaster.set(ControlMode.PercentOutput, LMotor);
  // }
  public void driveRMAX(double RMotor) {
    motorR1.set(RMotor);
	}
	public void driveLMAX(double LMotor){
		motorL1.set(LMotor);
	}
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new JoystickTankDrive());
  }
}
