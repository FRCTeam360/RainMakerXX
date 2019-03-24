/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
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


/**
 * An example command.  You can replace me with your own command.
 */
public class MoveArm extends Command {
  double target;
  double position;

  boolean button1;
  boolean button2;
  boolean button3;
  // boolean low;
  // boolean mid;
  // boolean high;
  public MoveArm() {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);
    requires(Robot.arm);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    target = 0;
    position = RobotMap.armMotor.getSelectedSensorPosition();

    button1 = false;
    button2 = false;
    button3 = false;
    // low = false;
    // mid = false;
    // high = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    button1 = OI.joyControl.getRawButton(1);
    button2 = OI.joyControl.getRawButton(3);
    button3 = OI.joyControl.getRawButton(4);

    // if(button1 || low){
    //   position = Constants.armLow;
    //   low = true;
    //   if(button2 || button3){
    //     low = false;
    //     mid = button2;
    //     high = button3;
    //   }
    // }else if(button2 || mid){
    //   position = Constants.armMid;
    //   mid = true;
    //   if(button1 || button3){
    //     low = button1;
    //     mid = false;
    //     high = button3;
    //   }
    // }else if(button3 || high){
    //   position = Constants.armHigh;
    //   high = true;
    //   if(button2 || button1){
    //     low = button1;
    //     mid = button2;
    //     high = false;
    //   }
    // }

    if(button1){
      position = Constants.armLow;
    }else if(button2){
      position = Constants.armMid;
    }else if(button3){
      position = Constants.armHigh;
    }else{
      if(Math.abs(OI.joyControl.getRawAxis(1)) >= .1){
        Robot.arm.articulateArm(OI.joyControl.getRawAxis(1));
        position = RobotMap.armMotor.getSelectedSensorPosition();
      } else{
        Robot.arm.setArmPosition(position);
      }
    }

    

    // if(OI.joyControl.getRawButton(1)){
    //   target = OI.joyControl.getRawAxis(1) * 4096 * 10.0;
    //   Robot.arm.setArmPosition(target);
    // }else{
    //   target = 0;
      // if(Math.abs(OI.joyControl.getRawAxis(1)) >= .1){
      //   Robot.arm.articulateArm(OI.joyControl.getRawAxis(1) * -1);
      // }
      // Robot.arm.articulateArm(0);
    // }
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
