/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;

public class CameraMode extends Command {
  private boolean isFinished = false;

  boolean togglePressCamera = false;
  boolean toggleOn = false;

  public CameraMode() {
    requires(Robot.limelight);
  }

  @Override
  protected void initialize() {
    
  }

  @Override
  protected void execute() {
    // if (OI.joyR.getRawButtonPressed(4)){
    //   if(Constants.auto) {
    //     NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
    //   } else {
    //     NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1.0);
    //   }
    // }
    if(OI.joyControl.getRawButton(4)){
      if(!togglePressCamera){
          toggleOn = !toggleOn;
          togglePressCamera = true;
      }
    }else{
      togglePressCamera = false;
    }
    if(toggleOn) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
    } else {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1.0);
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
