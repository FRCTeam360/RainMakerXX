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
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.WristManual;

/**
 * Add your docs here.
 */
public class WristControl extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX wristMotor = RobotMap.wristMotor;

  private int zeroSensor;

  private final int kSlotIdx = 0;
	private final int kPIDLoopIdx = 0;
  private final int kTimeoutMs = 10;
  
  public WristControl(){
    wristMotor.setInverted(false);
    wristMotor.setNeutralMode(NeutralMode.Brake);

    wristMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
    wristMotor.setSensorPhase(false);

    wristMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
    wristMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
    
    wristMotor.configNominalOutputForward(0, kTimeoutMs);
		wristMotor.configNominalOutputReverse(0, kTimeoutMs);
		wristMotor.configPeakOutputForward(1, kTimeoutMs);
    wristMotor.configPeakOutputReverse(-1, kTimeoutMs);
    
    wristMotor.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
		wristMotor.config_kF(0, Constants.wristF, kTimeoutMs);
		wristMotor.config_kP(0, Constants.wristP, kTimeoutMs);
		wristMotor.config_kI(0, Constants.wristI, kTimeoutMs);
    wristMotor.config_kD(0, Constants.wristD, kTimeoutMs);
    
    // wristMotor.configContinuousCurrentLimit(70, 10);
  }

  public void setMotor(double speed) {
		wristMotor.set(ControlMode.PercentOutput, speed);
	}
	public void setMotorPosition(double distance) {
		wristMotor.set(ControlMode.MotionMagic, distance);
	}
	public void stop() {
		wristMotor.set(ControlMode.PercentOutput, 0);
	}
	public double getPosition() {
		return wristMotor.getSelectedSensorPosition(0);
	}
	public void motionMagicInit() {
		/* set acceleration and vcruise velocity - see documentation */
		wristMotor.configMotionCruiseVelocity(1100, kTimeoutMs);
		wristMotor.configMotionAcceleration(1500, kTimeoutMs);
		/* zero the sensor */
		//elevatorMaster.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
  }
  public void Process(){
    SmartDashboard.putNumber("WristVel", wristMotor.getSelectedSensorVelocity(0));
	  SmartDashboard.putNumber("WristPos",  getPosition());
	  SmartDashboard.putNumber("WristOutputPercent", wristMotor.getMotorOutputPercent());
	  SmartDashboard.putNumber("WristError", wristMotor.getClosedLoopError(0));
  }
	
	public void zeroWrist() {
		wristMotor.setSelectedSensorPosition(0, 0, kTimeoutMs);
	}
	
	public double getMotorOutputVoltage() {
		return wristMotor.getMotorOutputVoltage();
	}
	
	public void getCurrentPosition() {
		RobotMap.currentPos = wristMotor.getSelectedSensorPosition(kPIDLoopIdx);
  }
	
	public int wristIsFine() {
		return wristMotor.getSensorCollection().getPulseWidthRiseToRiseUs();
  }
	
	public void wristOutputIsFine() {
		if(wristIsFine() == 0) {
			DriverStation.reportWarning("Wrist encoder is NOT working, automatic control disabled", false);
			stop();
		}
	}

  public void articulateWrist(double wristMotor){
    // if(OI.joyControl.getRawButton(12)){

    //   RobotMap.wristMotor.set(ControlMode.PercentOutput, wristMotor);
      
    // }else if(Constants.armPanelPickUpActivation == false){
      
    //   int armEncode = RobotMap.armMotor.getSelectedSensorPosition();
    //   int wristEncode = (int) (RobotMap.wristMotor.getSelectedSensorPosition() * 1.3);

    //   if((armEncode + wristEncode) > Constants.wristThreshold){

    //     RobotMap.wristMotor.set(ControlMode.PercentOutput, (-1 * Constants.wristSpeed));

    //   }else if(((armEncode + wristEncode)) < -1 * (Constants.wristThreshold)){

    //     RobotMap.wristMotor.set(ControlMode.PercentOutput, .6 * Constants.wristSpeed);

    //   } else{

    //     RobotMap.wristMotor.set(ControlMode.PercentOutput, 0);
    //   }
    // }else{

    //   int armEncode = RobotMap.armMotor.getSelectedSensorPosition();
    //   int wristEncode = (int) ((RobotMap.wristMotor.getSelectedSensorPosition() + Constants.wristPanelPickUp) * 1.3);

    //   if((armEncode + wristEncode) > Constants.wristThreshold){

    //     RobotMap.wristMotor.set(ControlMode.PercentOutput, (-1 * Constants.wristSpeed));

    //   }else if((armEncode + wristEncode) < -1 * (Constants.wristThreshold)){

    //     RobotMap.wristMotor.set(ControlMode.PercentOutput, .6 * Constants.wristSpeed);

    //   } else{

    //     if(armEncode > 2000){
    //       RobotMap.wristMotor.set(ControlMode.PercentOutput, -.09);
    //     }else if(armEncode > 400){
    //       RobotMap.wristMotor.set(ControlMode.PercentOutput, -.04);
    //     }else{

    //       RobotMap.wristMotor.set(ControlMode.PercentOutput, 0);
    //     }
    //   }
    // }
  }
  public void wristReset(){
    RobotMap.wristMotor.set(ControlMode.PercentOutput, 0);
    RobotMap.wristMotor.setSelectedSensorPosition(Constants.wristResetPosition);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new WristManual());
  }
}
