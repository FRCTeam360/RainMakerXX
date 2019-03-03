/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.ArmManual;

/**
 * Add your docs here.
 */
public class ArmControl extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public TalonSRX armTalon = RobotMap.armMotor;

  private final int kSlotIdx = 0;
	private final int kPIDLoopIdx = 0;
  private final int kTimeoutMs = 10;
  
  public ArmControl(){
    armTalon.setInverted(true);
    armTalon.setNeutralMode(NeutralMode.Brake);

    armTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
    armTalon.setSensorPhase(false);

    // armTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
    // armTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
    
    // armTalon.configNominalOutputForward(0, kTimeoutMs);
		// armTalon.configNominalOutputReverse(0, kTimeoutMs);
		// armTalon.configPeakOutputForward(1, kTimeoutMs);
    // armTalon.configPeakOutputReverse(-1, kTimeoutMs);
    
    // armTalon.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
		// armTalon.config_kF(0, Constants.armF, kTimeoutMs);
		// armTalon.config_kP(0, Constants.armP, kTimeoutMs);
		// armTalon.config_kI(0, Constants.armI, kTimeoutMs);
    // armTalon.config_kD(0, Constants.armD, kTimeoutMs);
    
    // wristMotor.configContinuousCurrentLimit(70, 10);
  }

  public void setMotor(double speed) {
    armTalon.set(ControlMode.PercentOutput, speed);
    Process();
	}
	// public void setMotorPosition(double position) {
  //   armTalon.set(ControlMode.MotionMagic, position);
  //   Process();
	// }
	public void stop() {
		armTalon.set(ControlMode.PercentOutput, 0);
	}
	public double getPosition() {
		return armTalon.getSelectedSensorPosition(0);
	}
	// public void motionMagicInit() {
	// 	armTalon.configMotionCruiseVelocity(460, kTimeoutMs);
  //   armTalon.configMotionAcceleration(642, kTimeoutMs);
  // }
  public void Process(){
    SmartDashboard.putNumber("ArmVel", armTalon.getSelectedSensorVelocity(0));
	  SmartDashboard.putNumber("ArmPos",  getPosition());
	  SmartDashboard.putNumber("ArmOutputPercent", armTalon.getMotorOutputPercent());
	  SmartDashboard.putNumber("ArmError", armTalon.getClosedLoopError(0));
  }
	
	public void zeroWrist() {
		armTalon.setSelectedSensorPosition(0, 0, kTimeoutMs);
	}
	
	public double getMotorOutputVoltage() {
		return armTalon.getMotorOutputVoltage();
	}
	
	public void getCurrentPosition() {
		RobotMap.currentArmPos = armTalon.getSelectedSensorPosition(kPIDLoopIdx);
  }
	
	public int armIsFine() {
		return armTalon.getSensorCollection().getPulseWidthRiseToRiseUs();
  }
	
	public void armOutputIsFine() {
		if(armIsFine() == 0) {
			DriverStation.reportWarning("Arm encoder is NOT working, automatic control disabled", false);
			stop();
		}
	}

  
  public void armReset(){
    RobotMap.wristMotor.set(ControlMode.PercentOutput, 0);
    RobotMap.wristMotor.setSelectedSensorPosition(0);
  }


  // public ArmControl(){

  //   armTalon.setNeutralMode(NeutralMode.Brake);
  //   armTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  //   armTalon.setSensorPhase(false);
  // }

  public void articulateArm(double armMotor){

    armTalon.set(ControlMode.PercentOutput, armMotor);
  }

  // public double armPosition(){

  //   return armTalon.getSelectedSensorPosition();
  // }

  // public void resetArm(){
    
  //   armTalon.set(ControlMode.PercentOutput, 0);
  //   armTalon.setSelectedSensorPosition(0);
  // }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // setDefaultCommand(new ArmManual());
  }
}
