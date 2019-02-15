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
  public static Joystick joyDrive = new Joystick(0);
  public static Joystick joyControl = new Joystick(1);

  public static Button autoShift = new JoystickButton(joyDrive, 10);
  public static Button manualShift = new JoystickButton(joyDrive, 9);

  public static Button buttonUp = new JoystickButton(joyDrive, 12);
  public static Button buttonDown = new JoystickButton(joyDrive, 11);

  public OI() {
    buttonUp.whenPressed(new ShiftUp());
    buttonDown.whenPressed(new ShiftDown());

    autoShift.whenPressed(new AutoShift());
    manualShift.whenPressed(new ManualShift());
  }
}
