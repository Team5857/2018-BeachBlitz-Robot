package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {

	public DriveWithJoystick() {
		super("DriveWithJoystick");
		requires(Robot.driveTrain);
	}
	
	protected void initialize() {
	}
	
	protected void execute() {
		Robot.driveTrain.tankDrive(Robot.oi.getDriveStick(), Robot.oi.getSecondaryStick());
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {}
	
	protected void interrupted() {
		end();
	}

}
