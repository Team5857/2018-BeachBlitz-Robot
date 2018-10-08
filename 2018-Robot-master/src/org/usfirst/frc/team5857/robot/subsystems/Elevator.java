package org.usfirst.frc.team5857.robot.subsystems;

import org.usfirst.frc.team5857.robot.commands.ToggleElevator;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {
	public static SpeedController elevator1;
	public static SpeedController elevator2;
	
	public Elevator() {
		elevator1 = new WPI_TalonSRX(14);					
		elevator2 = new WPI_TalonSRX(15);
	}


	public void toggleElevator(Joystick secondaryStick) {
		elevator1.set(secondaryStick.getRawAxis(1));
		((BaseMotorController) elevator2).follow((IMotorController) elevator1);
	}

	public double getElevator1Speed() {
		return elevator1.get();
	}

	public double getElevator2Speed() {
		return elevator2.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ToggleElevator());
	}
}
