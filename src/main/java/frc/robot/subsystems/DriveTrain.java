/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class DriveTrain extends Subsystem {

  public TalonSRX motorRMaster = RobotMap.motorRightMaster;
  private TalonSRX motorRSlave = RobotMap.motorRightSlave;
  
  public TalonSRX motorLMaster = RobotMap.motorLeftMaster;
  private TalonSRX motorLSlave = RobotMap.motorLeftSlave;

  // public CANSparkMax motorRMaster = RobotMap.motorRightMaster;
	// private CANSparkMax motorRSlave = RobotMap.motorRightSlave;
	
	// public CANSparkMax motorLMaster = RobotMap.motorLeftMaster;
  // private CANSparkMax motorLSlave = RobotMap.motorLeftSlave;

  // private CANEncoder rightOne;
  // private CANEncoder rightTwo;
  // private CANEncoder leftOne;
  // private CANEncoder leftTwo;
  
  public DriveTrain() {

    motorRSlave.follow(motorRMaster);
    motorLSlave.follow(motorLMaster);
    
    motorLMaster.setInverted(false);
		motorLSlave.setInverted(false);
		
		motorRMaster.setInverted(true);
		motorRSlave.setInverted(true);
		
		motorLMaster.setSensorPhase(false);
    motorRMaster.setSensorPhase(false);
    
    motorLMaster.selectProfileSlot(0, 0);
		motorRMaster.selectProfileSlot(0, 0);
		
		// resetTalons(motorRMaster);
		// resetTalons(motorLMaster);
		
		// resetTalons(motorRSlave);
    // resetTalons(motorLSlave);
  }
  public void driveR(double RMotor) {
		motorRMaster.set(ControlMode.PercentOutput, RMotor);
	}
	public void driveL(double LMotor){
		motorLMaster.set(ControlMode.PercentOutput, LMotor);
  }
  public void brakeMode() {
    Robot.driveTrain.motorLMaster.setNeutralMode(NeutralMode.Brake);
    Robot.driveTrain.motorRMaster.setNeutralMode(NeutralMode.Brake);
    Robot.driveTrain.motorLSlave.setNeutralMode(NeutralMode.Brake);
    Robot.driveTrain.motorRSlave.setNeutralMode(NeutralMode.Brake);

    // Robot.driveTrain.motorLMaster.setIdleMode(IdleMode.kBrake);
    // Robot.driveTrain.motorRMaster.setIdleMode(IdleMode.kBrake);
    // Robot.driveTrain.motorLSlave.setIdleMode(IdleMode.kBrake);
    // Robot.driveTrain.motorRSlave.setIdleMode(IdleMode.kBrake);
  }
  public void coastMode() {
    Robot.driveTrain.motorLMaster.setNeutralMode(NeutralMode.Coast);
    Robot.driveTrain.motorRMaster.setNeutralMode(NeutralMode.Coast);
    Robot.driveTrain.motorLSlave.setNeutralMode(NeutralMode.Coast);
    Robot.driveTrain.motorRSlave.setNeutralMode(NeutralMode.Coast);

    // Robot.driveTrain.motorLMaster.setIdleMode(IdleMode.kCoast);
    // Robot.driveTrain.motorRMaster.setIdleMode(IdleMode.kCoast);
    // Robot.driveTrain.motorLSlave.setIdleMode(IdleMode.kCoast);
    // Robot.driveTrain.motorRSlave.setIdleMode(IdleMode.kCoast);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new JoystickTankDrive());
  }
}