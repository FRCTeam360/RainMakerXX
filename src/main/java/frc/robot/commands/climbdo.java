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
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class climbdo extends Command {
  public climbdo() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.climby);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.climby.driveR.setInverted(true);
    Robot.climby.driveL.setInverted(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.climby.getEncoders();
    Robot.climby.getDifference();
    Robot.climby.toggleMode();
    if(OI.joyControl.getRawButton(10))  {
      if(Math.abs(OI.joyControl.getRawAxis(1)) >= .05) {
        if(Robot.climby.getFrontEncoder() <= 100) {
          if(OI.joyControl.getRawAxis(1) < 0) {
            Robot.climby.lift1(-OI.joyControl.getRawAxis(1) * .5);
          } else {
            Robot.climby.lift1(0);
          }
        }
        if(Robot.climby.getFrontEncoder() >= 28000) {
          if(OI.joyControl.getRawAxis(1) > 0) {
            Robot.climby.lift1(-OI.joyControl.getRawAxis(1) * .5);
          } else {
            Robot.climby.lift1(0);
          }
        } else {
          Robot.climby.lift1(-OI.joyControl.getRawAxis(1) * .5);
        }
        //Robot.climby.lift1(-OI.joyControl.getRawAxis(1) * .5);
      } else {
        Robot.climby.lift1(0);
      }

      if(Math.abs(OI.joyControl.getRawAxis(3)) >= .05) {
        if(Robot.climby.getBackEncoder() <= 100) {
          if(OI.joyControl.getRawAxis(3) < 0) {
            Robot.climby.lift2(-OI.joyControl.getRawAxis(3) * .5);
          } else {
            Robot.climby.lift2(0);
          }
        }
        if(Robot.climby.getBackEncoder() >= 28000) {
          if(OI.joyControl.getRawAxis(3) > 0) {
            Robot.climby.lift2(-OI.joyControl.getRawAxis(3) * .5);
          } else {
            Robot.climby.lift2(0);
          }
        } else {
          Robot.climby.lift2(-OI.joyControl.getRawAxis(3) * .5);
        }
        //Robot.climby.lift2(OI.joyControl.getRawAxis(3) * .5);
      } else {
        Robot.climby.lift2(0);
      }

      if(OI.joyControl.getRawButton(6)) {
        Robot.climby.BottomRightDrive(.5);
      } else {
        Robot.climby.BottomRightDrive(0);
      }

      if(OI.joyControl.getRawButton(5)) {
        Robot.climby.BottomLeftDrive(.5);
      } else {
        Robot.climby.BottomLeftDrive(0);
      }

      while(OI.joyControl.getRawButton(8)) {
        Robot.climby.lift1Encoder((1 - (Robot.climby.getDifference()/5000)) * -1, 26000);
        Robot.climby.lift2Encoder((1 + (Robot.climby.getDifference()/5000)) * -1, 26000);
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
    end();
  }
}
