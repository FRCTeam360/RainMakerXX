/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveToTarget extends Command {
  public MoveToTarget() {
    requires(Robot.limelight);
  }

  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("Move To Target");
    if(Robot.limelight.getY() < -2.0) {
      Robot.driveTrain.driveL(-0.3);
      Robot.driveTrain.driveR(-0.3);
      System.out.println("run to target");
    // } else if (Robot.limelight.getX() > 0.5 && Robot.limelight.getX() < -0.5) {
    //   if(Robot.limelight.getX() > 0.5) {
    //     System.out.println("turn right");
    //     Robot.driveTrain.driveR(-0.2);
    //     Robot.driveTrain.driveL(0.2);
    //   } else if(Robot.limelight.getX() < -0.5) {
    //     System.out.println("turn left");
    //     Robot.driveTrain.driveL(-0.2);
    //     Robot.driveTrain.driveR(0.2);
    //   } else {
    //     System.out.println("motor stop");
    //     Robot.driveTrain.driveL(0);
    //     Robot.driveTrain.driveR(0);
    //   }
    } else {
      System.out.println("Stop");
      Robot.driveTrain.driveL(0);
      Robot.driveTrain.driveR(0);
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
