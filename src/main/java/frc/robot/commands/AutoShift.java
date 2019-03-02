/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class AutoShift extends Command {
  public AutoShift() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //requires(Robot.shifter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Constants.isInAutoShift = true;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Constants.isInAutoShift == true){
      double getRightVelocity = RobotMap.right1Motor.getEncoder().getVelocity();
      double getLeftVelocity = RobotMap.left1Motor.getEncoder().getVelocity();

      if(Math.abs(getRightVelocity) >= Constants.highShiftPoint && Math.abs(getLeftVelocity) >= Constants.highShiftPoint){

        Robot.driveTrain.driveRMAX(0);
        Robot.driveTrain.driveLMAX(0);

        Robot.shifter.shiftUp();
      } else if(Math.abs(getRightVelocity) <= Constants.lowShiftPoint && Math.abs(getLeftVelocity) <= Constants.lowShiftPoint){

        Robot.driveTrain.driveRMAX(0);
        Robot.driveTrain.driveLMAX(0);
        
        Robot.shifter.shiftDown();
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return OI.joyR.getRawButton(1);
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
