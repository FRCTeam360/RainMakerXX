/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftDown extends Command {
  public LiftDown() {
   requires(Robot.climby);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    Robot.climby.lift1(0);
    Robot.climby.lift2(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
