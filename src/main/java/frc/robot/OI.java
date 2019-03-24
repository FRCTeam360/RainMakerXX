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

  public static JoystickButton armLow = new JoystickButton(joyControl, 1);
  public static JoystickButton armMid = new JoystickButton(joyControl, 3);
  public static JoystickButton armHigh = new JoystickButton(joyControl, 4);

  public static JoystickButton wristOffset = new JoystickButton(joyControl, 12);

  public static JoystickButton intakeIn = new JoystickButton(joyControl, 5);
  public static JoystickButton intakeOut = new JoystickButton(joyControl, 7);

  public static JoystickButton hatchButton = new JoystickButton(joyControl, 6);

  public OI() {

    // armLow.whenPressed(new ArmPositioning(Constants.armLow));
    // armMid.whenPressed(new ArmPositioning(Constants.armMid));
    // armHigh.whenPressed(new ArmPositioning(Constants.armHigh));

    // wristOffset.whenPressed(new WristPanelPosition());

    // hatchButton.whenPressed(new HatchControl());

    intakeIn.whenPressed(new IntakeControl(-.5, 5));
    intakeOut.whenPressed(new IntakeControl(.5, 7));
  }
}
