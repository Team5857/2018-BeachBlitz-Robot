
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
		
		/**Logitech Extreme 3D Pro**/
		new JoystickButton(driveStick, 5).whenPressed(new ShiftDown());			//L bump
		new JoystickButton(driveStick, 6).whenPressed(new ShiftUp());				//R bump
	}
	
	public Joystick getDriveStick() {
		return driveStick;
	}
	
	
	public Joystick getSecondaryStick() {
		return secondaryStick;
	}
	

}

