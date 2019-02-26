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

  public static Button buttonDriveUp = new JoystickButton(joyR, 1);
  public static Button buttonDriveDown = new JoystickButton(joyL, 1);

  public static Button buttonMoveWithLime = new JoystickButton(joyR, 3);

  public static Button changeCamMode = new JoystickButton(joyR, 4);

  public static Button autoShift = new JoystickButton(joyR, 2);
  public static Button manualShift = new JoystickButton(joyL, 3);

  
  public OI() {
    buttonMoveWithLime.whenPressed(new MoveToTarget());

    changeCamMode.whenPressed(new CameraMode());

    buttonDriveUp.whenPressed(new ShiftUp());
		buttonDriveDown.whenPressed(new ShiftDown());
  
    autoShift.whenPressed(new AutoShift());
    manualShift.whenPressed(new ManualShift());
  }
}
