/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;


public class Robot extends TimedRobot {
  public static Shifter shifter;
  public static Pneumatics pneumatics;
  public static DriveTrain driveTrain;
  public static OI oi;
  public static ClimbingThing climbingThing;

  //Command m_autonomousCommand;
  //SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    shifter = new Shifter();
		pneumatics = new Pneumatics();
    driveTrain = new DriveTrain();
    climbingThing = new ClimbingThing();
    oi = new OI();

    Robot.climbingThing.resetLifters();
    
    
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
    //Robot.climbingThing.resetLifters();
    // RobotMap.lift1Motor.getSensorCollection().setQuadraturePosition(0, 0);
    // RobotMap.lift2Motor.getSensorCollection().setQuadraturePosition(0, 0);
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}