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
  boolean togglePressed = false;
  boolean toggleOn = false;
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
      if(!togglePressed){
          toggleOn = !toggleOn;
          togglePressed = true;
      }
    }else{
      togglePressed = false;
    }
    if(toggleOn){
      offset = Constants.wristHatchOffset;
      Constants.panelPickUpActivation = true;
    }else{
      offset = 0;
    }
    if(!OI.joyControl.getRawButton(1)){
      Robot.wrist.positionWrist(((RobotMap.armMotor.getSelectedSensorPosition()) * .85) - 2700 + offset);
    }else{
      Robot.wrist.positionWrist(-3390);
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
