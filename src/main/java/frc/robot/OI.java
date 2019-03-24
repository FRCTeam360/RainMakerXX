/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

public class OI {
  public static Joystick joyR = new Joystick(0);
	public static Joystick joyL = new Joystick(1);
  public static Joystick joyControl = new Joystick(2);

  public static JoystickButton ballIntake = new JoystickButton(joyControl, 1);

  public OI() {
    ballIntake.whenPressed(new BallPickUp());
  }
}
