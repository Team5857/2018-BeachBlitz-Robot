package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AUTO_RELEASE_INTAKE extends Command {

	public AUTO_RELEASE_INTAKE() {
		super("AUTO_RELEASE_INTAKE");
		requires(Robot.intake);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.intake.autoReleaseIntake();
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