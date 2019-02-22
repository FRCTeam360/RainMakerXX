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
import frc.robot.commands.ShiftDown;
import frc.robot.commands.ShiftUp;
import frc.robot.commands.ClimbingThingDo;
import frc.robot.commands.ClimbingThingDo1;
import frc.robot.commands.ClimbingThingDo2;

public class OI {
  public static Joystick joyR = new Joystick(0);
	public static Joystick joyL = new Joystick(1);
  public static Joystick joyOI = new Joystick(2);

  public static Button buttonUp = new JoystickButton(joyOI, 12);
  public static Button buttonDown = new JoystickButton(joyOI, 11);

  public static Button buttonLift1 = new JoystickButton(joyOI, 13);
  public static Button buttonLift2 = new JoystickButton(joyOI, 14);
  public static Button buttonLift3 = new JoystickButton(joyOI, 15);

  public OI() {
    buttonUp.whenPressed(new ShiftUp());
    buttonDown.whenPressed(new ShiftDown());
    //buttonLift1.whenPressed(new ClimbingThingDo());
    //buttonLift2.whenPressed(new ClimbingThingDo1());
    //buttonLift3.whenPressed(new ClimbingThingDo2());
  }
}
