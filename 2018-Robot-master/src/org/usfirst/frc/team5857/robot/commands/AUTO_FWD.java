package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AUTO_FWD extends Command {

	public AUTO_FWD() {
		super("AUTO_FWD");
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		//parameters: speed, left compensation, right compensation, seconds
		//make sure all are decimals
		
		//speed is between 0 and 1, percentage of motor output
		//rightComp and leftComp: compensation factors; to correct for motors going at different speeds
		//seconds is how long to drive
		Robot.drivetrain.autoDriveAtSpeed(.3, 1.0, 1.0, 5.0);
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {}
	
	protected void interrupted()
	{
		end();
	}

}
