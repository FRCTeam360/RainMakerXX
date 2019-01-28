/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.RobotMap;
import frc.robot.commands.Pressurize;

public class Pneumatics extends Subsystem {
  private Compressor comp = RobotMap.compressor;
  public void pressurize(){
    comp.start();
  }
  public void stop(){
  	comp.stop();
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Pressurize());
  }
}
