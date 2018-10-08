package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithRightJoystick extends Command {

	public DriveWithRightJoystick() {
		super("DriveWithJoystick");
		requires(Robot.drivetrainright);
	}
	
	protected void initialize() {
	}
	
	protected void execute() {
		Robot.drivetrainright.tankDrive(Robot.oi.getSecondaryStick());
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {}
	
	protected void interrupted() {
		end();
	}

}
