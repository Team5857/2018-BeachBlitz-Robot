package org.usfirst.frc.team5857.robot.subsystems;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5857.robot.commands.DriveWithLeftJoystick;

import com.ctre.phoenix.motorcontrol.can.*;


public class DriveTrainLeft extends Subsystem {
	public static SpeedController left1;
	public static SpeedController left2;
	public DriveTrainLeft() {
		left1 = new WPI_TalonSRX(2);					//initialize left motors on port 12
		left2 = new WPI_TalonSRX(3);
	}
		
	public void tankDrive(Joystick driveStick) {
		left1.set(-driveStick.getRawAxis(1)/2);					//left y-axis
		left2.set(-driveStick.getRawAxis(1)/2);
	}

	public void tankDrive(Joystick left, Joystick right)
	{
		DriveTrainLeft.left1.set(left.getRawAxis(1)/2);
		DriveTrainLeft.left2.set(left.getRawAxis(1)/2);
	}

	public void autoDriveAtSpeed(double speed, double leftComp, double seconds) {
		left1.set(-leftComp*speed);
		left2.set(-leftComp*speed);
		
		Timer.delay(seconds);
		
		//stop
		left1.set(0);
		left2.set(0);
	}

	public double getLeftSpeed() {
		return left1.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithLeftJoystick());
	}
	
}
	