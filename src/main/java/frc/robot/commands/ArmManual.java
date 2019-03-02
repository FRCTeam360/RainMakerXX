/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class ArmManual extends Command {
  public ArmManual() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.armControl);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }


  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    int wristOffset = 0;
    if(Constants.armPanelPickUpActivation == true){
      wristOffset = Constants.wristPanelPickUp;
    }

    Robot.wristControl.setMotorPosition((.77 * RobotMap.armMotor.getSelectedSensorPosition() + 200 + wristOffset));

    if(Math.abs(OI.joyControl.getRawAxis(1)) >= .15){
      Robot.armControl.setMotor(OI.joyControl.getRawAxis(1) * -.75);
    } else{
        Robot.armControl.setMotor(Constants.armStaySpeed);
    }

    // Robot.wristControl.setMotorPosition((-1 * RobotMap.armMotor.getSelectedSensorPosition()) + 300);
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
