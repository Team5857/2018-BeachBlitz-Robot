package org.usfirst.frc.team5857.robot.subsystems;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team5857.robot.commands.DriveWithRightJoystick;

import com.ctre.phoenix.motorcontrol.can.*;


public class DriveTrainRight extends Subsystem {
	public static SpeedController right1;
	public static SpeedController right2;
	public DriveTrainRight() {
		right1 = new WPI_TalonSRX(13);					//initialize right motors on port 2
		right2 = new WPI_TalonSRX(12);
	}

	public void tankDrive(Joystick secondaryStick) {
		right1.set(secondaryStick.getRawAxis(1)/2);					//Right y-axis
		right2.set(secondaryStick.getRawAxis(1)/2);
}
	public void tankDrive(Joystick left, Joystick right){
		DriveTrainRight.right1.set(right.getRawAxis(-1)/2);
		DriveTrainRight.right2.set(right.getRawAxis(1)/2);
	}
	public void autoDriveAtSpeed(double speed, double rightComp, double seconds) {
		right1.set(-rightComp*speed);
		right2.set(-rightComp*speed);
		
		Timer.delay(seconds);
		
		//stop
		right1.set(0);
		right2.set(0);
	}
		
	public double getRightSpeed() {
		return right1.get();
	}

	public void initDefaultCommand() {

		setDefaultCommand(new DriveWithRightJoystick());
	}
	
}
	