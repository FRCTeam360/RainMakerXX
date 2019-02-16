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

  BuiltInAccelerometer accel = RobotMap.accell;
  DigitalInput limitSwitch1 = RobotMap.limitTop1;
  DigitalInput limitSwitch2 = RobotMap.limitTop2;
  DigitalInput limitSwitch3 = RobotMap.limitBottom1;
  DigitalInput limitSwitch4 = RobotMap.limitBottom2;
  DigitalInput limitSwitch5 = RobotMap.limitFront1;
  DigitalInput limitSwitch6 = RobotMap.limitFront2;
  DigitalInput limitSwitch7 = RobotMap.limitBack1;
  DigitalInput limitSwitch8 = RobotMap.limitBack2;

  //public Victor one = RobotMap.lift1Motor;
  //public Victor two = RobotMap.lift2Motor;

  double accelerationX;
  double accelerationY;
  double accelerationZ;
  double pitch;
  double roll;

  //This is the max angle the robot can be off by
  public static final int maxAngle = 5;
  //Duh

  public void init(){
    accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);    
    double power = Math.log(100)/Math.log(maxAngle);
  }

  public void getAccel(){
    accelerationX = accel.getX();
    accelerationY = accel.getY();
    accelerationZ = accel.getZ();

    SmartDashboard.putNumber("Accel X: ", accelerationX);
    SmartDashboard.putNumber("Accel Y: ", accelerationY);
    SmartDashboard.putNumber("Accel Z: ", accelerationZ);

    pitch = Math.atan2((- accelerationX),Math.sqrt(accelerationY*accelerationY+accelerationZ*accelerationZ)) * 57.3;
    //roll = Math.atan2(accelerationY, accelerationZ) * 57.3;

    SmartDashboard.putNumber("Pitch : ", pitch);
    //SmartDashboard.putNumber("Roll : ", roll);
  }

  public void iDunnoMane(){
    pitch = Math.atan2((- accelerationX),Math.sqrt(accelerationY*accelerationY+accelerationZ*accelerationZ)) * 57.3;

    if(pitch > 5 || pitch < -5){
      SmartDashboard.putBoolean("Lift?", false);
    }else{ SmartDashboard.putBoolean("Lift?", true);}

    /*
    pitch to power 2.86
    5 to 2.86 roughly equals 100 so 5 &< is less then 100    
    */

  }

  public void climb(){
    pitch = Math.atan2((- accelerationX),Math.sqrt(accelerationY*accelerationY+accelerationZ*accelerationZ)) * 57.3;
    while(!limitSwitch1.get() && !limitSwitch2.get()){
      if(pitch < maxAngle){
        //set victor speed to 1-pitch to power of power/100
      } else {
        //set victor to 0
      }
      if(pitch > -maxAngle){
        //set victor speed to 1-pitch to power of power/100
      } else {
        //set victor to 0
      }
    }
  }
  
  public void getToLedge(){
    climb();
    while(!limitSwitch5.get() && !limitSwitch6.get()){
      //set victor forward
    }
  }

  public void retractOne(){
    getToLedge();
    while(!limitSwitch3.get()){
      //set victor up
    }
  }

  public void getToLedge2(){
    climb();
    while(!limitSwitch7.get() && !limitSwitch8.get()){
      //set victor forward
    }
  }

  public void retractTwo(){
    getToLedge();
    while(!limitSwitch4.get()){
      //set victor up
    }
  }

  public void driveForward(){
    retractTwo();
    //stuff goes in here
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ClimbingThingDo());
  }
}
