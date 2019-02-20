/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MoveWrist extends Command {

  public double pos;
  double wantedPos;
  
  public MoveWrist(double wantedPosition) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.wristControl);
		wantedPos = wantedPosition;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Constants.armPanelPickUpActivation == true){
      RobotMap.wristOffset = Constants.wristPanelPickUp;
    }
    RobotMap.goalWristPos = wantedPos + RobotMap.wristOffset;
    RobotMap.shouldWristStop = false;
    if(wantedPos == 0) {
    	RobotMap.shouldWristStop = true;
    }
    pos = wantedPos;	
	  Robot.wristControl.motionMagicInit();
	  Robot.wristControl.setMotorPosition(pos);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.wristControl.Process();
    SmartDashboard.putNumber("Wrist Position", Robot.wristControl.getPosition());
   	SmartDashboard.putNumber("Future Position", pos);
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
