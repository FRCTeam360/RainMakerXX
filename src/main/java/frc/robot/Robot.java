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
import frc.robot.commands.*;
import frc.robot.subsystems.*;


public class Robot extends TimedRobot {
  public static Shifter shifter;
  public static Limelight limelight;
  public static Pneumatics pneumatics;
  public static DriveTrain driveTrain;
  public static ArmControl armControl;
  public static WristControl wristControl;
  public static IntakeControl intakeControl;
  public static HatchPanelSolenoid hatchPanelSolenoid;
  public static OI oi;

  Command autonomousCommand;
  //SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    shifter = new Shifter();
    limelight = new Limelight();
		pneumatics = new Pneumatics();
    driveTrain = new DriveTrain();
    armControl = new ArmControl();
    wristControl = new WristControl();
    intakeControl = new IntakeControl();
    hatchPanelSolenoid = new HatchPanelSolenoid();
    oi = new OI();

    autonomousCommand = new SandstromPeriod();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
    Robot.driveTrain.coastMode();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    Robot.limelight.driveCamera();
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    Robot.limelight.visionCamera();
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
    Robot.driveTrain.brakeMode();
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }
    Robot.shifter.shiftDown();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
