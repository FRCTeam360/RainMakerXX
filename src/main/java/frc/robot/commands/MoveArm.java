/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;


/**
 * An example command.  You can replace me with your own command.
 */
public class MoveArm extends Command {
  double target = 0;
  double position = RobotMap.armMotor.getSelectedSensorPosition();
  double offset = 0;

  boolean button1 = false;
  boolean button2 = false;
  boolean button3 = false;
  boolean low = false;
  boolean mid = false;
  boolean high = false;
  public MoveArm() {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);
    requires(Robot.arm);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    button1 = OI.joyControl.getRawButton(2);
    button2 = OI.joyControl.getRawButton(3);
    button3 = OI.joyControl.getRawButton(4);

    if(Constants.panelPickUpActivation){
      offset = Constants.armHatchOffset;
    }else{
      offset = 0;
    }

    // if(button1 || low){
    //   position = Constants.armLow + offset;
    //   low = true;
    //   if(button2 || button3){
    //     low = false;
    //     mid = button2;
    //     high = button3;
    //   }
    // }else if(button2 || mid){
    //   position = Constants.armMid + offset;
    //   mid = true;
    //   if(button1 || button3){
    //     low = button1;
    //     mid = false;
    //     high = button3;
    //   }
    // }else if(button3 || high){
    //   position = Constants.armHigh + offset;
    //   high = true;
    //   if(button2 || button1){
    //     low = button1;
    //     mid = button2;
    //     high = false;
    //   }
    // }else{
    //   if(Math.abs(OI.joyControl.getRawAxis(1)) >= .1){
    //     Robot.arm.articulateArm(OI.joyControl.getRawAxis(1));
    //     position = RobotMap.armMotor.getSelectedSensorPosition();
    //     low = false;
    //     mid = false;
    //     high = false;
    //   }else{
    //     Robot.arm.setArmPosition(position);
    //   }
    // }

    if(button1){
      position = Constants.armLow + offset;
    }else if(button2){
      position = Constants.armMid + offset;
    }else if(button3){
      position = Constants.armHigh + offset;
    }else{
      if(Math.abs(OI.joyControl.getRawAxis(1)) >= .1){
        Robot.arm.articulateArm(OI.joyControl.getRawAxis(1));
        position = RobotMap.armMotor.getSelectedSensorPosition();
      } else{
        Robot.arm.setArmPosition(position);
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
