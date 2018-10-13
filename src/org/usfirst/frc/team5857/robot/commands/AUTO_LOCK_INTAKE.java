package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AUTO_LOCK_INTAKE extends Command {

	public AUTO_LOCK_INTAKE() {
		super("AUTO_LOCK_INTAKE");
		requires(Robot.intake);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.intake.autoLockIntake();
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