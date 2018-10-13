package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AUTO_TURN extends Command {

	public AUTO_TURN() {
		super("AUTO_TURN");
		requires(Robot.driveTrain);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.driveTrain.autoDriveTurn();
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