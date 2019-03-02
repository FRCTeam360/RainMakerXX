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

    if(Math.abs(OI.joyR.getRawAxis(1)) >= .05){
    	Robot.driveTrain.driveRMAX(-1 * OI.joyR.getRawAxis(1) * 0.2);
    }else{
    	Robot.driveTrain.driveRMAX(0);
    }
    if(Math.abs(OI.joyL.getRawAxis(1)) >= .05){
      Robot.driveTrain.driveLMAX(-1 * OI.joyL.getRawAxis(1) * 0.2);
    }else{
    	Robot.driveTrain.driveLMAX(0);
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
    end();
  }
}
