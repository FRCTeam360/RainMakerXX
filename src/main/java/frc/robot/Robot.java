/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.*;


public class Robot extends TimedRobot {
  
  
  
  //Command m_autonomousCommand;
  //SendableChooser<Command> m_chooser = new SendableChooser<>();

  public static Arm arm;
  public static Wrist wrist;
  public static HatchSolenoid hatchPanel;
  public static Intake intake;
  public static OI oi;

  
      

  @Override
  public void robotInit() {
    

    //m_chooser.addDefault("Default Auto", new ExampleCommand());
    // chooser.addObject("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);

    arm = new Arm();
    wrist = new Wrist();
    hatchPanel = new HatchSolenoid();
    intake = new Intake();
    oi = new OI();
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



    Robot.arm.resetEncoder();
    Robot.wrist.resetEncoder();
  }

  @Override
  public void teleopPeriodic() {

    Scheduler.getInstance().run();

  }

  @Override
  public void testPeriodic() {
  }
}