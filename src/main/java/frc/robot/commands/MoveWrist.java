/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class MoveWrist extends Command {
  double target;
  public MoveWrist() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    target = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if(Math.abs(OI.joyControl.getRawAxis(3)) >= .1){
    //   Robot.wrist.moveWrist(OI.joyControl.getRawAxis(3));
    // } else{
    //   Robot.wrist.moveWrist(0);
    // }

    if(OI.joyControl.getRawButton(2)){
      target = OI.joyControl.getRawAxis(3) * 4096 * 10;
      Robot.wrist.positionWrist(target);
    } else {
      target = 0;
      Robot.wrist.moveWrist(0);
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
