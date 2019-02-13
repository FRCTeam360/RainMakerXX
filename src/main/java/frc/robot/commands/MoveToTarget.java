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
  private int finish = 0;
  private double turnConstant = 0.15;
  public MoveToTarget() {
    requires(Robot.limelight);
  }

  @Override
  protected void initialize() {
    Robot.shifter.shiftDown();
    Robot.driveTrain.brakeMode();
    finish = 0;
  }

  
  @Override
  protected void execute() {
    System.out.println("Move To Target");
    if(Robot.limelight.getY() < -3.0) {
      if(Robot.limelight.getX() > 0.8) {
        System.out.println("turn right");
        Robot.driveTrain.driveL(0.3 + turnConstant);
        Robot.driveTrain.driveR(0.3 - turnConstant);
      } else if(Robot.limelight.getX() < -0.8) {
        System.out.println("turn left");
        Robot.driveTrain.driveL(0.3 - turnConstant);
        Robot.driveTrain.driveR(0.3 + turnConstant);
      } else {
        Robot.driveTrain.driveL(0.3);
        Robot.driveTrain.driveR(0.3);
        System.out.println("run to target");
      }
    } else {
      System.out.println("Stop");
      Robot.driveTrain.driveL(0);
      Robot.driveTrain.driveR(0);
      finish = 1;
      isFinished();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finish == 1;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.coastMode();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
