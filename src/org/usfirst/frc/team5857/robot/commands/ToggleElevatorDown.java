package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleElevatorDown extends Command {
	
	public ToggleElevatorDown()
	{
		super("ToggleElevator");
		requires(Robot.elevator);
	}
	
	protected void initialize() {}
	
	protected void execute() {
		Robot.elevator.toggleElevatorDown(Robot.oi.getSecondaryStick());
		
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {}
	
	protected void interrupted() {
		end();
	}
		
	

}
