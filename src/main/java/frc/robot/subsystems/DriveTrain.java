/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANEncoder;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.ShiftState;
import frc.robot.commands.JoystickTankDrive;

public class DriveTrain extends Subsystem {

  public CANSparkMax motorR1 = RobotMap.right1Motor;
  public CANSparkMax motorR2 = RobotMap.right2Motor;
  public CANSparkMax motorL1 = RobotMap.left1Motor;
  public CANSparkMax motorL2 = RobotMap.left2Motor;

  public CANEncoder rightOne;
  public CANEncoder rightTwo;
  public CANEncoder leftOne;
  public CANEncoder leftTwo;

  // initializes velocities for left and right sides
  double leftVel;
  double rightVel;

  // initializes new positions for left and right sides
  double leftNewPos;
  double rightNewPos;

  // initializes old position values to zero
  double leftOldPos = 0;
  double rightOldPos = 0;

  // initializes the output variable to zero for left and right sides
  double leftOutput = 0;
  double rightOutput = 0;

  // initializes the changes in positions for left and right sides
  double deltaRightPos;
  double deltaLeftPos;

  public DriveTrain() {

    // makes the second motor for left and right sides to follow the primary motor on the left and right
    motorL2.follow(motorL1);
    motorR2.follow(motorR1);
    
    // makes one side of the robot reverse direction in order to ensure that the robot goes forward when the joysticks are both forward and backwards when the joysticks are both backwards
    motorL1.setInverted(false);
    motorL2.setInverted(false);
    motorR1.setInverted(true);
    motorR2.setInverted(true);
  }

  public void leftEnc(){
    // gets the new position of the encoder
    leftNewPos = motorL1.getEncoder().getPosition();
    // puts raw number in smartdashboard
    SmartDashboard.putNumber("Left Raw Pos", leftNewPos);
    // finds the difference in the new and the old position
    deltaLeftPos = leftNewPos - leftOldPos;

    // gets the velocity of the left motor
    leftVel = motorL2.getEncoder().getVelocity();
    // puts raw number in smartdashboard
    SmartDashboard.putNumber("Left Raw Vel", leftVel);

    // checks if the shiftstate is up
    if(RobotMap.shiftState == ShiftState.UP){
    // takes the change in position and divides it by the high gear ratio
      deltaLeftPos /= Constants.highFactor;
    // takes the velocity and divides it by the high gear ratio
      leftVel /= Constants.highFactor;
    }

    // checks if the shiftstate is down
    if(RobotMap.shiftState == ShiftState.DOWN){
    // takes the change in position and divides it by the low gear ratio
      deltaLeftPos /= Constants.lowFactor;
    // takes the velocity and divides it by the low gear ratio
      leftVel /= Constants.lowFactor;
    }

    // sets the old position to the new position
    leftOldPos = leftNewPos;
    // adds the change in position to the left output
    leftOutput += deltaLeftPos;
    // outputs position in smartdashboard
    SmartDashboard.putNumber("Left Pos", leftOutput);
    // outputs velocity in smartdashboard
    SmartDashboard.putNumber("Left Vel", leftVel);

    SmartDashboard.putNumber("Wrist Amp Draw", RobotMap.pdp.getCurrent(15));
  }

  public void rightEnc(){
    // gets the new position of the encoder
    rightNewPos = motorR1.getEncoder().getPosition();
    // puts raw number in smartdashboard
    SmartDashboard.putNumber("Right Raw Pos", rightNewPos);
    // finds the difference in the new and the old position
    deltaRightPos = rightNewPos - rightOldPos;

    // gets the velocity of the left motor
    rightVel = motorR2.getEncoder().getVelocity();
    // puts raw number in smartdashboard
    SmartDashboard.putNumber("Right Raw Vel", rightVel);

    if(RobotMap.shiftState == ShiftState.UP){
    // takes the change in position and divides it by the high gear ratio
      deltaRightPos /= Constants.highFactor;
    // takes the velocity and divides it by the high gear ratio
      rightVel /= Constants.highFactor;
    }
      
    if(RobotMap.shiftState == ShiftState.DOWN){
    // takes the change in position and divides it by the low gear ratio
      deltaRightPos /= Constants.lowFactor;
    // takes the velocity and divides it by the low gear ratio
      rightVel /= Constants.lowFactor;
    }
  
    // sets the old position to the new position
    rightOldPos = rightNewPos;
    // adds the change in position to the left output
    rightOutput += deltaRightPos;
    // outputs position in smartdashboard
    SmartDashboard.putNumber("Right Pos", rightOutput);
    // outputs velocity in smartdashboard
    SmartDashboard.putNumber("Right Vel", rightVel);
  }

  public void driveRMAX(double RMotor) {
    // sets the primary motor on the right side is set to the speed set by the joystick
    motorR1.set(RMotor);
    // makes the rightEnc method run and put the numbers in smartdashboard
    rightEnc();
    // SmartDashboard.putNumber("Arm encoder", RobotMap.armMotor.getSelectedSensorPosition());
	}
	public void driveLMAX(double LMotor){
    // sets the primary motor on the left side is set to the speed set by the joystick
    motorL1.set(LMotor);
    // makes the leftEnc method run and put the numbers in smartdashboard
    leftEnc();
  }
  public void brakeMode() {
    Robot.driveTrain.motorL1.setIdleMode(IdleMode.kBrake);
    Robot.driveTrain.motorR1.setIdleMode(IdleMode.kBrake);
    Robot.driveTrain.motorL2.setIdleMode(IdleMode.kBrake);
    Robot.driveTrain.motorR2.setIdleMode(IdleMode.kBrake);
  }
  public void coastMode() {
    Robot.driveTrain.motorL1.setIdleMode(IdleMode.kCoast);
    Robot.driveTrain.motorR1.setIdleMode(IdleMode.kCoast);
    Robot.driveTrain.motorL2.setIdleMode(IdleMode.kCoast);
    Robot.driveTrain.motorR2.setIdleMode(IdleMode.kCoast);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new JoystickTankDrive());
  }
}