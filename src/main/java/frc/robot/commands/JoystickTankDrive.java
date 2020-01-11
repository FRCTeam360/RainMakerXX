/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class JoystickTankDrive extends Command {
  public JoystickTankDrive() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if(Math.abs(OI.joyR.getRawAxis(1)) > .05) {
      Robot.driveTrain.driveR(-1 * OI.joyR.getRawAxis(1));
    } else {
      Robot.driveTrain.driveR(0);
    }
    if(Math.abs(OI.joyL.getRawAxis(1)) > .05) {
      Robot.driveTrain.driveL(-1 * OI.joyL.getRawAxis(1));
    } else {
      Robot.driveTrain.driveL(0);
    }

    // if(Math.abs(OI.joyR.getRawAxis(1)) > .05) {
    //   Robot.driveTrain.drive(-1 * OI.joyR.getRawAxis(1));
    // } else {
    //   Robot.driveTrain.drive(0);
    // }
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
