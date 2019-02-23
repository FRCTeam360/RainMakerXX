/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Pressurize extends Command {

  Timer timer;
  boolean shouldRun;

  public Pressurize() {
    requires(Robot.pneumatics);
  }

  @Override
  protected void initialize() {
    timer = new Timer();
	  shouldRun = true;
	  timer.reset();
	  timer.stop();
  }

  @Override
  protected void execute() {
    if(shouldRun == true && RobotController.getInputVoltage() > 10) {
      Robot.pneumatics.pressurize(); 	
    } else if (shouldRun == true && ! (RobotController.getInputVoltage() > 10)) {
      shouldRun = false;
      Robot.pneumatics.stop();
      timer.reset();
      timer.start();
    }
    if (timer.get() > 0.5) {
      timer.reset();
      timer.stop();
      shouldRun = true;
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    timer.stop();
	  timer.reset();
    Robot.pneumatics.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
