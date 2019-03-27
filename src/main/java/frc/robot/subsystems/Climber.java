/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.climbdo;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public TalonSRX climb1 = RobotMap.climbM1;
  public TalonSRX climb2 = RobotMap.climbM2;

  public TalonSRX driveR = RobotMap.liftMotorR;
  public TalonSRX driveL = RobotMap.liftMotorL;

  public void lift1(double power){
    climb1.set(ControlMode.PercentOutput, power);
  }

  public void lift2(double power) {
    climb2.set(ControlMode.PercentOutput, -power);
  }

  public void lift1Encoder(double power, double position) {
    if(getFrontEncoder() <= position) {
      lift1(power);
    } else {
      lift1(0);
    }
  }

  public void lift2Encoder(double power, double position) {
    if(getBackEncoder() <= position) {
      lift2(power);
    } else {
      lift2(0);
    }
  }

  public double getDifference() {
    double difference = getFrontEncoder() - getBackEncoder();
    SmartDashboard.putNumber("difference", difference);
    return difference;
  }

  public void getEncoders() {
    SmartDashboard.putNumber("Back Encoder", climb2.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Front Encoder", climb1.getSelectedSensorPosition(0));
  }

  public void resetEncoders() {
    climb1.setSelectedSensorPosition(0);
    climb2.setSelectedSensorPosition(0);
  }

  public double getFrontEncoder() {
    return climb1.getSelectedSensorPosition(0);
  }

  public double getBackEncoder() {
    return climb2.getSelectedSensorPosition(0);
  }

  public void BottomRightDrive(double power) {
    driveR.set(ControlMode.PercentOutput, power);
  }

  public void BottomLeftDrive(double power) {
    driveL.set(ControlMode.PercentOutput, power);
  }

  public void toggleMode() {
    if(OI.joyControl.getRawButton(7)) {
        if(Constants.isClimbing) {
          Constants.isClimbing = false;
        } else {
          Constants.isClimbing = true;
        }
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //
    setDefaultCommand(new climbdo());
  }
}
