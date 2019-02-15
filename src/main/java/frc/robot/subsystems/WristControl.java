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
      
    }else if(Constants.armPanelPickUpActivation == false){
      
      int armEncode = RobotMap.armMotor.getSelectedSensorPosition();
      int wristEncode = (int) (RobotMap.wristMotor.getSelectedSensorPosition() * 1.3);

      if((armEncode + wristEncode) > Constants.wristThreshold){

        RobotMap.wristMotor.set(ControlMode.PercentOutput, (-1 * Constants.wristSpeed));

      }else if(((armEncode + wristEncode)) < -1 * (Constants.wristThreshold)){

        RobotMap.wristMotor.set(ControlMode.PercentOutput, Constants.wristSpeed);

      } else{

        RobotMap.wristMotor.set(ControlMode.PercentOutput, 0);
      }
    }else{

      int armEncode = RobotMap.armMotor.getSelectedSensorPosition();
      int wristEncode = (int) ((RobotMap.wristMotor.getSelectedSensorPosition() + Constants.wristPanelPickUp) * 1.3);

      if((armEncode + wristEncode) > Constants.wristThreshold){

        RobotMap.wristMotor.set(ControlMode.PercentOutput, (-1 * Constants.wristSpeed));

      }else if((armEncode + wristEncode) < -1 * (Constants.wristThreshold)){

        RobotMap.wristMotor.set(ControlMode.PercentOutput, Constants.wristSpeed);

      } else{

        RobotMap.wristMotor.set(ControlMode.PercentOutput, 0);
      }
    }
  }
  public void wristReset(){
    RobotMap.wristMotor.set(ControlMode.PercentOutput, 0);
    RobotMap.wristMotor.setSelectedSensorPosition(Constants.wristResetPosition);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new WristManual());
  }
}
