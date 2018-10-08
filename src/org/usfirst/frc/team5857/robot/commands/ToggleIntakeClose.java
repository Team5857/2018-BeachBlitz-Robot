package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleIntakeClose extends Command {
	
	public ToggleIntakeClose()
	{
		super("ToggleIntake");
		requires(Robot.intake);
	}
	
	protected void initialize() {}
	
	protected void execute() {
		Robot.intake.toggleIntakeClose(Robot.oi.getDriveStick(), Robot.oi.getSecondaryStick());
		
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {}
	
	protected void interrupted() {
		end();
	}
		
	

}
