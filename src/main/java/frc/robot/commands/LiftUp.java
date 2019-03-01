/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftUp extends Command {
  public LiftUp() {
    
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    
    Robot.climby.lift1Encoder(.5, 100);
    Robot.climby.lift2Encoder(-.5, 100);
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
