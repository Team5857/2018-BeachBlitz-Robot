package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AUTO_LONG_UP extends Command {

	public AUTO_LONG_UP() {
		super("AUTO_LONG_UP");
		requires(Robot.elevator);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.elevator.autoMoveElevatorUp("LONG");
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