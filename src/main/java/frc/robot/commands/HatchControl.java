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

public class HatchControl extends Command {
  boolean togglePressed = false;
  boolean toggleOn = false;
  public HatchControl() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hatchPanel);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(OI.joyControl.getRawButton(6) && !OI.joyControl.getRawButton(10)){
      if(!togglePressed){
          toggleOn = !toggleOn;
          togglePressed = true;
      }
    }else{
      togglePressed = false;
    }
    if(toggleOn && !Constants.defenseMode){
      Robot.hatchPanel.hatchIn();
    }else{
      Robot.hatchPanel.hatchOut();
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
