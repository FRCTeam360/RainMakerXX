/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

import javax.sound.midi.Soundbank;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import frc.robot.commands.*;
import frc.robot.subsystems.*;


public class Robot extends TimedRobot {
  public static Shifter shifter;
  public static Pneumatics pneumatics;
  public static DriveTrain driveTrain;
  public static OI oi;
  I2C i2c;
  
  //Command m_autonomousCommand;
  //SendableChooser<Command> m_chooser = new SendableChooser<>();
      

  @Override
  public void robotInit() {
    shifter = new Shifter();
		pneumatics = new Pneumatics();
		driveTrain = new DriveTrain();
    oi = new OI();
    i2c = new I2C(I2C.Port.kMXP, 0x39);

    //Set Sensor Settings and Enable
    i2c.write(0x80 | 0x0D, 0x00);
    i2c.write(0x80 | 0x0F, 0x20);
    i2c.write(0x80 | 0x01, 0xAD);
    i2c.write(0x80 | 0x00, 0x03);

    //m_chooser.addDefault("Default Auto", new ExampleCommand());
    // chooser.addObject("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  //  m_autonomousCommand = m_chooser.getSelected();
  //   if (m_autonomousCommand != null) {
  //     m_autonomousCommand.start();
  //   }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }

  }

  @Override
  public void teleopPeriodic() {

     //Color Values
     double R, G, B, C, IR;
     byte[] rgbc = {0, 0, 0, 0, 0, 0, 0, 0};

    for (int i = 0; i < 8; i++) {
      byte[] temp = {0};
      i2c.read(0x80 | 0x14+i, 1, temp);
      rgbc[i] = temp[0];
    }

    R = 512 * rgbc[3] + rgbc[2];
    G = 512 * rgbc[5] + rgbc[4];
    B = 512 * rgbc[7] + rgbc[6];
    C = 512 * rgbc[1] + rgbc[0];
  
    // Calculate IR component
    IR = ((R + G + B - C) / 2);
  
    // Remove IR components
    R = R - IR;
    G = G - IR;
    B = B - IR;
    C = C - IR;

    if (C > 80) {
      System.out.println("WHITE");
    }

    Scheduler.getInstance().run();

  }

  @Override
  public void testPeriodic() {
  }
}