/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;


/**
 * An example command.  You can replace me with your own command.
 */
public class MoveArm extends Command {
  double target = 0;
  double position = RobotMap.armMotor.getSelectedSensorPosition();
  double offset = 0;

  boolean button2 = false;
  boolean button3 = false;
  boolean button4 = false;
  boolean button1 = false;
  public MoveArm() {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);
    requires(Robot.arm);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    button1 = OI.joyControl.getRawButton(1);
    button2 = OI.joyControl.getRawButton(2);
    button3 = OI.joyControl.getRawButton(3);
    button4 = OI.joyControl.getRawButton(4);

    if(Constants.panelPickUpActivation){
      offset = 0;
    }else{
      offset = 0;
    }
    // System.out.println(RobotMap.armReset.get()); 
    if(!OI.joyControl.getRawButton(10)){
      if(button1 && !Constants.defenseMode){
        Robot.arm.setArmPosition(Constants.armIntake);
      }else if(button2 && !Constants.defenseMode){
        // Robot.arm.setArmPosition(Constants.armLow + offset - 75);
        Robot.arm.setArmPosition(Constants.armLow + offset);
      }else if(button3 && !Constants.defenseMode){
        // Robot.arm.setArmPosition(Constants.armMid + offset - 75);
        Robot.arm.setArmPosition(Constants.armMid + offset);
      }else if(button4 && !Constants.defenseMode){
        // Robot.arm.setArmPosition(Constants.armHigh + offset - 75);
        Robot.arm.setArmPosition(Constants.armHigh + offset);
      }else if(Constants.defenseMode){
        if(!RobotMap.armReset.get()){
          RobotMap.armMotor.setSelectedSensorPosition(0);
          Robot.arm.setArmPosition(0);
        }else{
          Robot.arm.setArmPosition(100);
        }
      }else if(button4 && !Constants.defenseMode){
        Robot.arm.setArmPosition(Constants.armHigh + offset);
      }else if(!Constants.defenseMode){
        if(Math.abs(OI.joyControl.getRawAxis(1)) >= .1){
          if(OI.joyControl.getRawAxis(1) <= -0.1){
            Robot.arm.articulateArm(OI.joyControl.getRawAxis(1) * .6);
          }else if(OI.joyControl.getRawAxis(1) >= 0.1){
            Robot.arm.articulateArm(OI.joyControl.getRawAxis(1) * .3);
          }else{
            Robot.arm.articulateArm(0);
            position = RobotMap.armMotor.getSelectedSensorPosition();
            Robot.arm.setArmPosition(position);
          }
          //position = RobotMap.armMotor.getSelectedSensorPosition();
        }else{
          Robot.arm.articulateArm(0);
          position = RobotMap.armMotor.getSelectedSensorPosition();
          Robot.arm.setArmPosition(position);
        }
      }else if(Constants.defenseMode){
        if(RobotMap.armReset.get()) {
          Robot.arm.articulateArm(.25);
        }else{
          Robot.arm.articulateArm(0);
          position = RobotMap.armMotor.getSelectedSensorPosition();
          Robot.arm.setArmPosition(position);
        }
      }
    }else{
      // Robot.arm.setArmPosition(0);
      Robot.arm.articulateArm(0);
      position = RobotMap.armMotor.getSelectedSensorPosition();
      Robot.arm.setArmPosition(position);
    }
    if(!RobotMap.armReset.get()){
      RobotMap.armMotor.setSelectedSensorPosition(0);
      // position = RobotMap.armMotor.getSelectedSensorPosition();
      // Robot.arm.setArmPosition(position);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
