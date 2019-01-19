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

  @Override
  protected void execute() {
    if(Robot.limelight.getX() > 0.5) {
      Robot.driveTrain.driveR(-0.25);
      Robot.driveTrain.driveL(0.25);
    } else if(Robot.limelight.getX() < -0.5) {
      Robot.driveTrain.driveL(-0.25);
      Robot.driveTrain.driveR(0.25);
    } else {
      if(Robot.limelight.getY() < -2.0) {
        Robot.driveTrain.driveL(-0.3);
        Robot.driveTrain.driveR(-0.3);
      } else {
        Robot.driveTrain.driveL(0.0);
        Robot.driveTrain.driveR(0.0);
      }
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
