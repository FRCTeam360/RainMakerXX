/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class CameraMode extends Command {
  private boolean isFinished = false;

  public CameraMode() {
    requires(Robot.limelight);
  }

  @Override
  protected void initialize() {
    
  }

  @Override
  protected void execute() {
    while (OI.joyR.getRawButton(4)){
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1.0);
    }
    isFinished = true;
  }

  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  @Override
  protected void end() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
