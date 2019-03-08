/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class ArmMiddle extends Command {
  boolean isDone;
  public ArmMiddle() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isDone = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    int hatchPanelOffset = 0;
    if(Constants.armPanelPickUpActivation == true){
      hatchPanelOffset = Constants.armHatchPanelOffset;
    }
    // if((RobotMap.armMotor.getSelectedSensorPosition() - Constants.armMidPosition - hatchPanelOffset) > Constants.armAutoThreshold){

    //   RobotMap.armMotor.set(ControlMode.PercentOutput, Constants.armAutoSpeedUp);
    // }else if((RobotMap.armMotor.getSelectedSensorPosition() - Constants.armMidPosition) < Constants.armAutoThreshold){
      
    //   RobotMap.armMotor.set(ControlMode.PercentOutput, Constants.armAutoSpeedDown);
    // }else{
    //   isDone = true;
    // }

    Robot.armControl.setMotorPosition(Constants.armMidPosition + hatchPanelOffset);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isDone;
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
