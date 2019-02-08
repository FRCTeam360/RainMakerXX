/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbingThingDo;
import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SensorUtil;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

/**
 * Add your docs here.
 */
public class ClimbingThing extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Channels

  BuiltInAccelerometer accel;
  DigitalInput limitSwitch1 = RobotMap.limit1;
  DigitalInput limitSwitch2 = RobotMap.limit2;
  public Victor one = RobotMap.lift1Motor;
  public Victor two = RobotMap.lift2Motor;

  double accelerationX;
  double accelerationY;
  double accelerationZ;
  double pitch;
  double roll;

  public void init(){

    accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);    
  }

  public void getAccel(){
    accelerationX = accel.getX();
    accelerationY = accel.getY();
    accelerationZ = accel.getZ();

    SmartDashboard.putNumber("Accel X: ", accelerationX);
    SmartDashboard.putNumber("Accel Y: ", accelerationY);
    //SmartDashboard.putNumber("Accel Z: ", accelerationZ);

    pitch = Math.atan2((- accelerationX),Math.sqrt(accelerationY*accelerationY+accelerationZ*accelerationZ)) * 57.3;
    roll = Math.atan2(accelerationY, accelerationZ) * 57.3;
    SmartDashboard.putNumber("Pitch : ", pitch);
    SmartDashboard.putNumber("Roll : ", roll);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ClimbingThingDo());
  }
}
