/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MoveWrist extends Command {
  double target;
  double offset;
  double position = RobotMap.wristMotor.getSelectedSensorPosition();
  boolean togglePressWrist = false;
  boolean toggleOn = false;
  boolean toggleDefense = false;
  boolean toggleOnDefense = false;
  public MoveWrist() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    target = 0;
    offset = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(OI.joyControl.getRawButton(12)){
      if(!togglePressWrist){
          toggleOn = !toggleOn;
          togglePressWrist = true;
      }
    }else{
      togglePressWrist = false;
    }

    if(OI.joyControl.getRawButton(9)){
      if(!toggleDefense){
          toggleOnDefense = !toggleOnDefense;
          toggleDefense = true;
      }
    }else{
      toggleDefense = false;
    }
    Constants.defenseMode = toggleOnDefense;

    if(toggleOn){
      offset = 0;
      Constants.panelPickUpActivation = false;
    }else{
      offset = Constants.wristHatchOffset;
      Constants.panelPickUpActivation = true;
    }
    if(OI.joyControl.getRawButton(1) && !Constants.defenseMode){
      Robot.wrist.positionWrist(-3800);
    }else if(Constants.defenseMode){
      Robot.wrist.positionWrist(-200);
    }else{
      // Robot.wrist.positionWrist(((RobotMap.armMotor.getSelectedSensorPosition()) * .85) - 1900 + offset);
      if(!OI.joyControl.getRawButton(10)){
        if(Math.abs(OI.joyControl.getRawAxis(3)) >= .05){
          Robot.wrist.moveWrist(-OI.joyControl.getRawAxis(3) * .3);
          position = RobotMap.wristMotor.getSelectedSensorPosition();
        }else{
          Robot.wrist.moveWrist(0);
          Robot.wrist.positionWrist(position);
        }
      }else{
        Robot.wrist.moveWrist(0);
        Robot.wrist.positionWrist(position);
      }
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
  }
}
