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

public class IntakeManual extends Command {
  public IntakeManual() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.intakeControl);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(!OI.joyControl.getRawButton(10)) {
    if(OI.joyControl.getRawButton(5)) {
      Robot.wristControl.setMotorPosition(-950);
      Robot.intakeControl.controlIntake(-.5);
      if(RobotMap.pdp.getCurrent(8) >= 12) {
        Constants.hasCargo = true;
      }
    } else if(OI.joyControl.getRawButton(6)){

      Robot.intakeControl.controlIntake(.5);
      Constants.hasCargo = false;

    } else if(Constants.hasCargo == true) {
      Robot.intakeControl.controlIntake(-.1);
    } else {
      
      Robot.intakeControl.controlIntake(0);
    }
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
