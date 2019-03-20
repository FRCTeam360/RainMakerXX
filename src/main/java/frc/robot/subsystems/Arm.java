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

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.MoveArm;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final int kSlotIdx = 0;
	private final int kPIDLoopIdx = 0;
  private final int kTimeoutMs = 10;

  public Arm(){
    RobotMap.armMotor.setInverted(false);
    RobotMap.armMotor.setNeutralMode(NeutralMode.Brake);

    RobotMap.armMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
    RobotMap.armMotor.setSensorPhase(true);

    RobotMap.armMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
    RobotMap.armMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

    RobotMap.armMotor.configNominalOutputForward(0, kTimeoutMs);
    RobotMap.armMotor.configNominalOutputReverse(0, kTimeoutMs);
		RobotMap.armMotor.configPeakOutputForward(1, kTimeoutMs);
    RobotMap.armMotor.configPeakOutputReverse(-1, kTimeoutMs);
    
    RobotMap.armMotor.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
		RobotMap.armMotor.config_kF(0, Constants.armF, kTimeoutMs);
		RobotMap.armMotor.config_kP(0, Constants.armP, kTimeoutMs);
		RobotMap.armMotor.config_kI(0, Constants.armI, kTimeoutMs);
    RobotMap.armMotor.config_kD(0, Constants.armD, kTimeoutMs);

    RobotMap.armMotor.configMotionCruiseVelocity(Constants.armVel, kTimeoutMs);
    RobotMap.armMotor.configMotionAcceleration(Constants.armAcc, kTimeoutMs);
  }

  public void setArmPosition(double position){
    RobotMap.armMotor.set(ControlMode.MotionMagic, position);
    Process();
  }

  public void resetEncoder(){
    RobotMap.armMotor.setSelectedSensorPosition(0);
  }

  public void articulateArm(double speed){
    RobotMap.armMotor.set(ControlMode.PercentOutput, speed);
    Process();
  }

  public void Process(){
    SmartDashboard.putNumber("ArmVel", RobotMap.armMotor.getSelectedSensorVelocity(0));
	  SmartDashboard.putNumber("ArmPos",  RobotMap.armMotor.getSelectedSensorPosition());
	  SmartDashboard.putNumber("ArmOutputPercent", RobotMap.armMotor.getMotorOutputPercent());
	  SmartDashboard.putNumber("ArmError", RobotMap.armMotor.getClosedLoopError(0));
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveArm());
  }
}