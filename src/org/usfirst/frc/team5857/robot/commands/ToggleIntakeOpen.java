package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleIntakeOpen extends Command {
	
	public ToggleIntakeOpen()
	{
		super("ToggleIntake");
		requires(Robot.intake);
	}
	
	protected void initialize() {}
	
	protected void execute() {
		Robot.intake.toggleIntakeOpen(Robot.oi.getDriveStick(), Robot.oi.getSecondaryStick());
		
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {}
	
	protected void interrupted() {
		end();
	}
		
	

}
