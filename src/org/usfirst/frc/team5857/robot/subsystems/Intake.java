package org.usfirst.frc.team5857.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team5857.robot.commands.ToggleIntakeOpen;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	public static SpeedController intake;
	public static SpeedController elevator1, elevator2;
	//private static boolean intakeup = false;
	
	public Intake() {
		intake = new WPI_TalonSRX(1);					//initialize left motors on port 12
		elevator1 = new WPI_TalonSRX(14);
		elevator2 = new WPI_TalonSRX(0);
	}

	public void toggleIntakeOpen(Joystick driveStick, Joystick secondaryStick) {
		//lock intake 
		if(driveStick.getRawButton(1)){
			intake.set(-1);
			Timer.delay(1);
			intake.set(-0.4);
		} 
		//unlock intake
		else if(driveStick.getRawButton(2)){
			intake.set(0); 
		}
	}
	
	public void toggleIntakeClose(Joystick driveStick, Joystick secondaryStick) {
		//opnes up the intake
		if(secondaryStick.getRawButton(1)){
			intake.set(1);
		} 
		//if not pressed
		else if(!secondaryStick.getRawButton(1) && !driveStick.getRawButton(1)){
			intake.set(0);
		}
	}	
	public boolean getIntakeSpeed() {
		return intake.getInverted();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new ToggleIntakeOpen());
	}
}
