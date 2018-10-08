package org.usfirst.frc.team5857.robot.subsystems;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5857.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.*;


public class DriveTrain extends Subsystem {
	public static SpeedController left1;
	public static SpeedController left2;
	public static SpeedController right1;
	public static SpeedController right2;
	
	public static double factor = 0.5;
	
	public DriveTrain() {
		left1 = new WPI_TalonSRX(12);					//initialize left motors on port 12
		left2 = new WPI_TalonSRX(13);
		right1 = new WPI_TalonSRX(2);					//initialize right motors on port 2
		right2 = new WPI_TalonSRX(3);
	}


	public void tankDrive(Joystick driveStick) {
		left1.set(-factor*driveStick.getRawAxis(1));					//left y-axis
		left2.set(-factor*driveStick.getRawAxis(1));
		right1.set(factor*driveStick.getRawAxis(5));					//Right y-axis
		right2.set(factor*driveStick.getRawAxis(5));
	}

	public void tankDrive(Joystick left, Joystick right)
	{
		DriveTrain.left1.set(-left.getRawAxis(1));
		DriveTrain.left2.set(-left.getRawAxis(1));
		DriveTrain.right1.set(-right.getRawAxis(5));
		DriveTrain.right2.set(-right.getRawAxis(5));
	}

	public void autoDriveAtSpeed(double speed, double rightComp, double leftComp, double seconds) {
		left1.set(-leftComp*speed);
		left2.set(-leftComp*speed);
		right1.set(rightComp*speed);
		right2.set(rightComp*speed);
		
		Timer.delay(seconds);
		
		//stop
		left1.set(0);
		left2.set(0);
		right1.set(0);
		right2.set(0);
	}
	
	public double getRightSpeed() {
		return right1.get();
	}

	public double getLeftSpeed() {
		return left1.get();
	}
	
	public void shiftUp() {
		if (factor < 0)
			factor = 0;	
		else if (factor > 1)
			factor = 1;
		if (factor < 1 && factor >= 0) {
			factor = factor + 0.05;
			Timer.delay(0.1);
		}
	}
	
	public void shiftDown() {
		if (factor < 0)
			factor = 0;	
		else if (factor > 1)
			factor = 1;
		if (factor < 1 && factor >= 0){
			factor = factor - 0.05;
			Timer.delay(0.1);
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
	
}
	