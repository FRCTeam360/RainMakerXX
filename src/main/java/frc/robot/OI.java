/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

public class OI {
  public static Joystick joyR = new Joystick(0);
	public static Joystick joyL = new Joystick(1);
  public static Joystick joyOI = new Joystick(2);

  public static Button buttonUp = new JoystickButton(joyR, 1);
  public static Button buttonDown = new JoystickButton(joyL, 1);

  public static Button buttonLiftDown = new JoystickButton(joyOI, 4);
  public static Button buttonLift1 = new JoystickButton(joyOI, 5);
  public static Button buttonLift2 = new JoystickButton(joyOI, 6);

  public OI() {
    buttonUp.whenPressed(new ShiftUp());
    buttonDown.whenPressed(new ShiftDown());
    buttonLiftDown.whenPressed(new Climb());
    buttonLift1.whenPressed(new ClimbingThingDo1());
    buttonLift2.whenPressed(new ClimbingThingDo2());
  }
}
