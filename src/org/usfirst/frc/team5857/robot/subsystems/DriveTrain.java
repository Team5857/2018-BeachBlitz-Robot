package org.usfirst.frc.team5857.robot.subsystems;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5857.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.*;
import java.util.*;


public class DriveTrain extends Subsystem {
	public String driveMode;
	public long time1, time2;
	public static SpeedController left1;
	public static SpeedController left2;
	public static SpeedController right1;
	public static SpeedController right2;
	public DriveTrain() {
		left1 = new WPI_TalonSRX(2);					//initialize left motors on port 12
		left2 = new WPI_TalonSRX(3);
		right1 = new WPI_TalonSRX(13);					//initialize left motors on port 12
		right2 = new WPI_TalonSRX(12);
		driveMode = "half_speed";
		//long time1 = System.currentTimeMillis(); //1
		//long time2 = System.currentTimeMillis(); //2
	}
		
	public void tankDrive(Joystick driveStick, Joystick secondaryStick) {
		if(secondaryStick.getRawButtonPressed(8)) {
			driveMode = "full_speed";
		}
		if(secondaryStick.getRawButtonPressed(7)) {
			driveMode = "half_speed";
		}
		
		if(driveMode.equals("half_speed")) {
			DriveTrain.left1.set(driveStick.getRawAxis(1)/2);
			DriveTrain.left2.set(driveStick.getRawAxis(1)/2);
			DriveTrain.right1.set(-driveStick.getRawAxis(5)/2);
			DriveTrain.right2.set(-driveStick.getRawAxis(5)/2);
		}
		else if(driveMode.equals("full_speed")) {
			DriveTrain.left1.set(driveStick.getRawAxis(1));
			DriveTrain.left2.set(driveStick.getRawAxis(1));
			DriveTrain.right1.set(-driveStick.getRawAxis(5));
			DriveTrain.right2.set(-driveStick.getRawAxis(5));

			if(secondaryStick.getRawButtonPressed(1)) {
				time1 = System.currentTimeMillis();
				DriveTrain.left1.set(1);
				DriveTrain.left2.set(1);
				DriveTrain.right1.set(-1);
				DriveTrain.right2.set(-1);
				if(secondaryStick.getRawButtonPressed(2)) {
					time2 = System.currentTimeMillis();
					DriveTrain.left1.set(0);
					DriveTrain.left2.set(0);
					DriveTrain.right1.set(0);
					DriveTrain.right2.set(0);
				}
				SmartDashboard.put("TIME", time2 - time1)
			}

			DriveTrain.left1.set(driveStick.getRawAxis(1));
			DriveTrain.left2.set(driveStick.getRawAxis(1));
			DriveTrain.right1.set(-driveStick.getRawAxis(5));
			DriveTrain.right2.set(-driveStick.getRawAxis(5));
		}
	}

	public void tankDrive1(Joystick left, Joystick right)
	{
		if(driveMode.equals("half_speed")) {
			DriveTrain.left1.set(left.getRawAxis(1)/2);
			DriveTrain.left2.set(left.getRawAxis(1)/2);
			DriveTrain.right1.set(-right.getRawAxis(5)/2);
			DriveTrain.right2.set(-right.getRawAxis(5)/2);
		}
		else if(driveMode.equals("full_speed")) {
			DriveTrain.left1.set(left.getRawAxis(1));
			DriveTrain.left2.set(left.getRawAxis(1));
			DriveTrain.right1.set(-right.getRawAxis(5));
			DriveTrain.right2.set(-right.getRawAxis(5));
		}
	}

	public void autoDriveForward(double speed, double rightComp, double leftComp, double seconds) {
		left1.set(leftComp*speed);
		left2.set(leftComp*speed);
		right1.set(-rightComp*speed);
		right2.set(-rightComp*speed);
		
		Timer.delay(seconds);
		
		//stop
		left1.set(0);
		left2.set(0);
		right1.set(0);
		right2.set(0);
	}

	public void autoDriveTurn() {
		left1.set(-1);
		left2.set(-1);
		right1.set(-1);
		right2.set(-1);
		
		Timer.delay(0.2);
		
		left1.set(0);
		left2.set(0);
		right1.set(0);
		right2.set(0);
	}

	public double getLeftSpeed() {
		return left1.get();
	}
	public double getRightSpeed() {
		return right1.get();
	}
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
	
}
	