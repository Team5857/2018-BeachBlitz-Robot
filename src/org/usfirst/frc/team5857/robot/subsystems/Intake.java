package org.usfirst.frc.team5857.robot.subsystems;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team5857.robot.commands.ToggleIntake;

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
	public boolean stateFlag, lockFlag;
	public int count;
	
	public Intake() {
		intake = new WPI_TalonSRX(1);
		elevator1 = new WPI_TalonSRX(14);
		elevator2 = new WPI_TalonSRX(0);
		stateFlag = false;
		lockFlag = false;

	}
	
	public void toggleIntake(Joystick driveStick, Joystick secondaryStick) {
		if(driveStick.getRawButtonPressed(6)){
			intake.set(1);
			lockFlag = true;
			stateFlag = true;
		} 

		if(driveStick.getRawButtonPressed(5)){
			lockFlag = false;
		}
		
		if(lockFlag) {
			Timer.delay(0.3);
			intake.set(0.3);
		}
		else if(!lockFlag && stateFlag == true) {
			intake.set(-0.2);
			Timer.delay(0.1);
			intake.set(0);
			stateFlag = false;
		}
	}	
	public boolean getIntakeSpeed() {
		return intake.getInverted();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new ToggleIntake());
	}
}
