/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Limelight extends Subsystem {
  
  public void changeCameraMode() {
    if (NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").getDouble(1) == 1) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
    } else {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
    }
  }

  public void runCamera() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  }

  @Override
  public void initDefaultCommand() {
    
  }
}