package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AUTO_SHORTSHORT_FWD extends Command {

	public AUTO_SHORTSHORT_FWD() {
		super("AUTO_SHORTSHORT_FWD");
		requires(Robot.driveTrain);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.driveTrain.autoDriveForward(1, 1, 1, 1);
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