/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.WristManual;

/**
 * Add your docs here.
 */
public class WristControl extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void articulateWrist(double wristMotor){
    if(OI.joyControl.getRawButton(12)){
      RobotMap.wristMotor.set(ControlMode.PercentOutput, wristMotor);
    }else{
      int armEncode = RobotMap.armMotor.getSelectedSensorPosition();
      int wristEncode = RobotMap.wristMotor.getSelectedSensorPosition();
      if((armEncode + wristEncode) > 50){
        RobotMap.wristMotor.set(ControlMode.PercentOutput, -.15);
      }else if((-(armEncode) - wristEncode) > 50){
        RobotMap.wristMotor.set(ControlMode.PercentOutput, .15);
      } else{
        RobotMap.wristMotor.set(ControlMode.PercentOutput, 0);
      }
    }
  }
  public void wristReset(){
    RobotMap.wristMotor.set(ControlMode.PercentOutput, 0);
    RobotMap.wristMotor.setSelectedSensorPosition(Constants.wristResetPosition);
    while(RobotMap.wristMotor.getSelectedSensorPosition() >= 0){
      RobotMap.wristMotor.set(ControlMode.PercentOutput, -.3);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new WristManual());
  }
}
