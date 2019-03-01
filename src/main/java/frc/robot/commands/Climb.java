/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Climb extends Command {
  public Climb() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis)
    requires(Robot.climbingThing);
 }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //boolean accelStatus = RobotMap.
    RobotMap.lift1Motor.getSensorCollection().setQuadraturePosition(0, 0);
    RobotMap.lift2Motor.getSensorCollection().setQuadraturePosition(0, 0);
    Robot.climbingThing.climb();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return RobotMap.lift1Motor.getSelectedSensorPosition(0) == 100;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.print("End");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
