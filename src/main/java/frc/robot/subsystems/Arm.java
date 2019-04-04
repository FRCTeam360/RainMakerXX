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

  public static TalonSRX arm = RobotMap.armMotor;

  public Arm(){
    arm.setInverted(false);
    arm.setNeutralMode(NeutralMode.Brake);

    arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
    arm.setSensorPhase(true);

    arm.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
    arm.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

    arm.configNominalOutputForward(0, kTimeoutMs);
    arm.configNominalOutputReverse(0, kTimeoutMs);
		arm.configPeakOutputForward(1, kTimeoutMs);
    arm.configPeakOutputReverse(-1, kTimeoutMs);

    arm.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
		arm.config_kF(0, Constants.armF, kTimeoutMs);
		arm.config_kP(0, Constants.armP, kTimeoutMs);
		arm.config_kI(0, Constants.armI, kTimeoutMs);
    arm.config_kD(0, Constants.armD, kTimeoutMs);

    arm.configMotionCruiseVelocity(Constants.armVel, kTimeoutMs);
    arm.configMotionAcceleration(Constants.armAcc, kTimeoutMs);

    arm.setSelectedSensorPosition(0);
  }

  public void setArmPosition(double position){
    arm.set(ControlMode.MotionMagic, position);
    Process();
  }

  // public void resetEncoder(){
  //   while(!RobotMap.armReset.get()){
  //     articulateArm(.3);
  //   }
  //   arm.setSelectedSensorPosition(200);
  //   setArmPosition(0);
  // }

  public void articulateArm(double speed){
    arm.set(ControlMode.PercentOutput, speed);
    Process();
  }

  public void Process(){
    SmartDashboard.putNumber("ArmVel", arm.getSelectedSensorVelocity(0));
	  SmartDashboard.putNumber("ArmPos",  arm.getSelectedSensorPosition());
	  SmartDashboard.putNumber("ArmOutputPercent", arm.getMotorOutputPercent());
    SmartDashboard.putNumber("ArmError", arm.getClosedLoopError(0));
    SmartDashboard.putBoolean("Limit Switch", RobotMap.armReset.get());
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveArm());
  }
}