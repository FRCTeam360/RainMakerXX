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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SensorUtil;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.OI;

public class ClimbingThing extends Subsystem {

  BuiltInAccelerometer accel = RobotMap.accell;

  public TalonSRX one = RobotMap.lift1Motor;
  public TalonSRX two = RobotMap.lift2Motor;
  //public Victor bone = RobotMap.bottom1;
  //public Victor btwo = RobotMap.bottom2;

  public Encoder lift1 = RobotMap.lift1;
  public Encoder lift2 = RobotMap.lift2;

  double accelerationX;
  double accelerationY;
  double accelerationZ;
  double pitch;
  double roll;
  double power;

  public static final int maxAngle = 5;
  public static final double dPerRev = 20.0;
  public static final double pPerRev = 1024.0;
  public static final double dPerPul = dPerRev/pPerRev;
  public static final double dUpLift = 100;
  //168

  public void init(){
    accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);    
    double power = Math.log(100)/Math.log(maxAngle);
    lift1.setDistancePerPulse(dPerPul);
    lift2.setDistancePerPulse(dPerPul);
  }

  public void getAccel(){
    accelerationX = accel.getX();
    accelerationY = accel.getY();
    accelerationZ = accel.getZ();

    SmartDashboard.putNumber("Accel X: ", accelerationX);
    SmartDashboard.putNumber("Accel Y: ", accelerationY);
    SmartDashboard.putNumber("Accel Z: ", accelerationZ);

    //pitch = Math.atan2((- accelerationX),Math.sqrt(accelerationY*accelerationY+accelerationZ*accelerationZ)) * 57.3;
    roll = Math.atan2(accelerationY, accelerationZ) * 57.3;

    SmartDashboard.putNumber("Roll : ", roll);
  }

  public void iDunnoMane(){
    pitch = Math.atan2(accelerationY, accelerationZ) * 57.3;


    if(pitch > 5 || pitch < -5){
      SmartDashboard.putBoolean("Lift?", false);
    }else{ SmartDashboard.putBoolean("Lift?", true);}

    /*
    pitch to power 2.86
    5 to 2.86 roughly equals 100 so 5 &< is less then 100    
    */

  }

  public void climb(){
    pitch = Math.atan2(accelerationY, accelerationZ) * 57.3;
    //lift1.getDistance() <= dUpLift || lift1.getDistance() <= dUpLift
    //OI.joyR.getRawAxis(1)) > .05
    while(OI.joyR.getRawAxis(1) > .05){
      System.out.print("asdfghjkjhgfdsasdfghjhgfdsdfghj");
      if(pitch <= maxAngle){
        one.set(ControlMode.PercentOutput, (1-Math.pow(pitch, power))/2);
      } else {
        one.set(ControlMode.PercentOutput,0);
      }
      if(pitch >= -maxAngle){
        two.set(ControlMode.PercentOutput, (1-Math.pow(pitch, power))/2);
      } else {
        two.set(ControlMode.PercentOutput,0);
      }
    }
  }

  public void retract(Encoder limit, TalonSRX victor){
    while(lift1.getDistance() >= -dUpLift || OI.joyOI.getRawAxis(1) < -.05){
      victor.set(ControlMode.PercentOutput,-1);
    }
  }

  public void retractOne(){
    retract(lift1, one);
  }

  public void retractTwo(){
    retract(lift2, two);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimbingThingDo());
  }
}
