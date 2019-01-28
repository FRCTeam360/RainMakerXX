/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AlignWithTarget extends Command {

  Timer timer = new Timer();

  public AlignWithTarget() {
    requires(Robot.limelight);
  }

  @Override
  protected void initialize() {
    timer.reset();
	  timer.stop();
  }

  @Override
  protected void execute() {
    timer.start();
    while(timer.get() < 10) {
      System.out.println("start loop");
      double x = Robot.limelight.getX();
      if(x > 0.5) {
        System.out.println("turn right");
        Robot.driveTrain.driveR(-0.2);
        Robot.driveTrain.driveL(0.2);
      } else if(x < -0.5) {
        System.out.println("turn left");
        Robot.driveTrain.driveL(-0.2);
        Robot.driveTrain.driveR(0.2);
      } else {
        System.out.println("motor stop");
        Robot.driveTrain.driveL(0);
        Robot.driveTrain.driveR(0);
      }
      if (timer.get() > 5 && Robot.driveTrain.motorLMaster.getMotorOutputVoltage() == 0 && Robot.driveTrain.motorRMaster.getMotorOutputVoltage() == 0) {
        System.out.println("end loop");
        timer.stop();
        timer.reset();
        break;
      }
    }
  }

  @Override
  protected boolean isFinished() {
    System.out.println("finsihed");
    return true;
  }

  @Override
  protected void end() {
    System.out.println("Ended");
    timer.stop();
    timer.reset();
    Robot.driveTrain.driveL(0);
    Robot.driveTrain.driveR(0);
  }

  @Override
  protected void interrupted() {
  }
}
