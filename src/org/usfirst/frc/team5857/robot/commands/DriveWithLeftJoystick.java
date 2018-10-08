package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithLeftJoystick extends Command {

	public DriveWithLeftJoystick() {
		super("DriveWithJoystick");
		requires(Robot.drivetrainleft);
	}
	
	protected void initialize() {
	}
	
	protected void execute() {
		Robot.drivetrainleft.tankDrive(Robot.oi.getDriveStick());
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {}
	
	protected void interrupted() {
		end();
	}

}
