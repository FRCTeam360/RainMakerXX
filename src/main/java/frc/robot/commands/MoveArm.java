/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MoveArm extends Command {

  public double pos;
  double wantedPos;
  
  public MoveArm(double wantedPosition) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.armControl);
		wantedPos = wantedPosition;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.goalArmPos = wantedPos;
    RobotMap.shouldArmStop = false;
    if(wantedPos == 0) {
    	RobotMap.shouldArmStop = true;
    }
    pos = wantedPos;	
	  // Robot.armControl.motionMagicInit();
	  // Robot.armControl.setMotorPosition(pos);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.armControl.Process();
    SmartDashboard.putNumber("Arm Position", Robot.armControl.getPosition());
   	SmartDashboard.putNumber("Future Arm Position", pos);
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
