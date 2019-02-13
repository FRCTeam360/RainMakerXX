/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.CameraMode;


public class Limelight extends Subsystem {

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");

  private double x = tx.getDouble(0.0);
  private double y = ty.getDouble(0.0);
  
  public void changeCameraMode() {
    if (NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").getDouble(1) == 1) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
    } else {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
    }
  }

  public double getX() {
    return tx.getDouble(0.0);
  }

  public double getY() {
    return ty.getDouble(0.0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CameraMode());
  }
}