
package org.usfirst.frc.team5857.robot;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team5857.robot.commands.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	public Joystick driveStick;
	public Joystick secondaryStick;
	
	public OI() {
		driveStick = new Joystick(0);				//Logitech Dual Action
		secondaryStick = new Joystick(1);
		//new JoystickButton(driveStick, 1).whenPressed(new ToggleIntakeClose());
		//new JoystickButton(secondaryStick, 1).whenPressed(new ToggleIntakeOpen());
		//new JoystickButton(driveStick, 2).whenPressed(new ToggleIntakeOpen());
		//new JoystickButton(secondaryStick, 3).whenPressed(new ToggleElevatorUp());
		//new JoystickButton(secondaryStick, 4).whenPressed(new ToggleElevatorDown());
		/**Logitech Extreme 3D Pro**/
		//R bump
	}
	
	public Joystick getDriveStick() {
		return driveStick;
	}
	
	
	public Joystick getSecondaryStick() {
		return secondaryStick;
	}
	

}

