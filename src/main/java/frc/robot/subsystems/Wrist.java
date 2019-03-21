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
import frc.robot.commands.MoveWrist;

/**
 * Add your docs here.
 */
public class Wrist extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX wrist = RobotMap.wristMotor;

  private final int kSlotIdx = 0;
	private final int kPIDLoopIdx = 0;
  private final int kTimeoutMs = 10;

  public Wrist(){

    wrist.setInverted(false);
    wrist.setNeutralMode(NeutralMode.Brake);

    wrist.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    wrist.setSensorPhase(false);
    
    wrist.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
    wrist.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

    wrist.configNominalOutputForward(0, kTimeoutMs);
    wrist.configNominalOutputReverse(0, kTimeoutMs);
		wrist.configPeakOutputForward(1, kTimeoutMs);
    wrist.configPeakOutputReverse(-1, kTimeoutMs);
    
    wrist.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
	  wrist.config_kF(0, Constants.wristF, kTimeoutMs);
		wrist.config_kP(0, Constants.wristP, kTimeoutMs);
		wrist.config_kI(0, Constants.wristI, kTimeoutMs);
    wrist.config_kD(0, Constants.wristD, kTimeoutMs);

    wrist.configMotionCruiseVelocity(Constants.wristVel, kTimeoutMs);
    wrist.configMotionAcceleration(Constants.wristAcc, kTimeoutMs);
  
  }

  public void moveWrist(double speed){
    RobotMap.wristMotor.set(ControlMode.PercentOutput, speed);
    Process();
  }

  public void resetEncoder(){
    wrist.setSelectedSensorPosition(0);
  }

  public void positionWrist(double position){
    wrist.set(ControlMode.MotionMagic, position);
    Process();
  }

  public void Process(){
    SmartDashboard.putNumber("WristVel", RobotMap.armMotor.getSelectedSensorVelocity(0));
	  SmartDashboard.putNumber("WristPos",  RobotMap.armMotor.getSelectedSensorPosition());
	  SmartDashboard.putNumber("WristOutputPercent", RobotMap.armMotor.getMotorOutputPercent());
	  SmartDashboard.putNumber("WristError", RobotMap.armMotor.getClosedLoopError(0));
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new MoveWrist());
  }
}
