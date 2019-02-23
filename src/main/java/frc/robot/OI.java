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

  public static Button buttonHatchPanelUp = new JoystickButton(joyControl, 7);
  public static Button buttonHatchPanelDown = new JoystickButton(joyControl, 8);

  public static Button buttonResetWrist = new JoystickButton(joyControl, 9);
  public static Button buttonResetArm = new JoystickButton(joyControl, 10);
  public static Button buttonManualWrist = new JoystickButton(joyControl, 12);

  public static Button buttonIntakeBall = new JoystickButton(joyControl, 5);
  public static Button buttonOutakeButton = new JoystickButton(joyControl, 6);

  public static Button buttonArmLow = new JoystickButton(joyControl, 2);
  public static Button buttonArmMid = new JoystickButton(joyControl, 3);
  public static Button buttonArmHigh = new JoystickButton(joyControl, 4);

  public static Button buttonWristPanelPosition = new JoystickButton(joyControl, 11);

  public OI() {
    buttonUp.whenPressed(new ShiftUp());
    buttonDown.whenPressed(new ShiftDown());

    buttonWristPanelPosition.whenPressed(new WristPanelPosition());

    autoShift.whenPressed(new AutoShift());

    buttonHatchPanelUp.whenPressed(new HatchIn());
    buttonHatchPanelDown.whenPressed(new HatchOut());

    buttonResetWrist.whenPressed(new WristReset());
    buttonResetArm.whenPressed(new ArmReset());

    buttonArmLow.whenPressed(new ArmLow());
    buttonArmMid.whenPressed(new ArmMiddle());
    buttonArmHigh.whenPressed(new ArmHigh());

    // buttonArmLow.whenPressed(new MoveArm(Constants.armLowPosition));
    // buttonArmMid.whenPressed(new MoveArm(Constants.armMidPosition));
    // buttonArmHigh.whenPressed(new MoveArm(Constants.armHighPosition));

    buttonManualWrist.whileHeld(new WristManual());
  }
}
