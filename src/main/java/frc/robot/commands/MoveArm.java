/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;


/**
 * An example command.  You can replace me with your own command.
 */
public class MoveArm extends Command {
  double target;
  public MoveArm() {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);
    requires(Robot.arm);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    target = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // int position = RobotMap.armMotor.getSelectedSensorPosition();
    // if(Math.abs(OI.joyControl.getRawAxis(1)) >= .1){
    //   Robot.arm.articulateArm(OI.joyControl.getRawAxis(1) * -1);
    //   position = RobotMap.armMotor.getSelectedSensorPosition();
    // } else{
      // Robot.arm.setArmPosition(position);
    // }

    if(OI.joyControl.getRawButton(1)){
      target = OI.joyControl.getRawAxis(1) * 4096 * 10.0;
      Robot.arm.setArmPosition(target);
    }else{
      target = 0;
      // if(Math.abs(OI.joyControl.getRawAxis(1)) >= .1){
      //   Robot.arm.articulateArm(OI.joyControl.getRawAxis(1) * -1);
      // }
      Robot.arm.articulateArm(0);
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
