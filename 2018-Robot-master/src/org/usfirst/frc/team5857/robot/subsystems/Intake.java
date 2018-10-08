package org.usfirst.frc.team5857.robot.subsystems;

import org.usfirst.frc.team5857.robot.commands.ToggleIntake;

import com.ctre.CANTalon;
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

public class Intake extends Subsystem {
	
	public static SpeedController leftIntake;
	public static SpeedController rightIntake;
	
	public Intake() {
		leftIntake = new WPI_TalonSRX(5);					//initialize left motors on port 12
		rightIntake = new WPI_TalonSRX(4);
	}
	
	public void toggleIntake(Joystick secondaryStick) {
		leftIntake.set(secondaryStick.getRawAxis(5));					//left y-axis
		((BaseMotorController) rightIntake).follow((IMotorController) leftIntake);
		//rightIntake.set(-secondaryStick.getRawAxis(1));
	}

	public double getRightIntakeSpeed() {
		return rightIntake.get();
	}

	public double getLeftIntakeSpeed() {
		return leftIntake.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ToggleIntake());
	}
}
