/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.*;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.RobotMap.ShiftState;
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

  public CANEncoder rightOne;
  public CANEncoder rightTwo;
  public CANEncoder leftOne;
  public CANEncoder leftTwo;

  double leftPos;
  double rightPos;
  double leftVel;
  double rightVel;

  public DriveTrain() {

    motorL2.follow(motorL1);
    motorR2.follow(motorR1);
    
    motorL1.setInverted(false);
    motorL2.setInverted(false);
    motorR1.setInverted(true);
    motorR2.setInverted(true);
  }

  public void rightEnc(){
    rightOne = motorR1.getEncoder();
    rightTwo = motorR2.getEncoder();

    rightVel = rightTwo.getVelocity();
    rightPos = rightOne.getPosition();

    SmartDashboard.putNumber("Right velocity before math: ", rightTwo.getVelocity());
    SmartDashboard.putNumber("Right position before math: ", rightOne.getPosition());

    if(RobotMap.shiftState == ShiftState.UP){
      rightVel /= Constants.highFactor;
      rightPos /= Constants.highFactor;
      
    } else if(RobotMap.shiftState == ShiftState.DOWN){
      rightVel /= Constants.lowFactor;
      rightPos /= Constants.lowFactor;

    }else{
      rightPos = 0;
      rightVel = 0;
    }
    SmartDashboard.putNumber("Right velocity after math: ", rightVel);
    SmartDashboard.putNumber("Right position after math: ", rightPos);
  }
  public void leftEnc(){
    leftOne = motorL1.getEncoder();
    leftTwo = motorL2.getEncoder();

    leftVel = leftTwo.getVelocity();
    leftPos = leftOne.getPosition();

    SmartDashboard.putNumber("Left velocity before math: ", leftVel);
    SmartDashboard.putNumber("Left position before math: ", leftPos);

    if(RobotMap.shiftState == ShiftState.UP){
      leftVel /= Constants.highFactor;
      leftPos /= Constants.highFactor;

    } else if(RobotMap.shiftState == ShiftState.DOWN){
      leftVel /= Constants.lowFactor;
      leftPos /= Constants.lowFactor;

    }else{
      leftVel = 0;
      leftPos = 0;
    }
    SmartDashboard.putNumber("Left velocity after math: ", leftVel);
    SmartDashboard.putNumber("Left position after math: ", leftPos);
  }
  public void driveRMAX(double RMotor) {
    motorR1.set(RMotor);
    rightEnc();
	}
	public void driveLMAX(double LMotor){
    motorL1.set(LMotor);
    leftEnc();
	}
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new JoystickTankDrive());
  }
}
