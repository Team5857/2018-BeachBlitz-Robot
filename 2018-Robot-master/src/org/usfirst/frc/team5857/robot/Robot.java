
package org.usfirst.frc.team5857.robot;

import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team5857.robot.commands.*;
import org.usfirst.frc.team5857.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static DriveTrain drivetrain;
	public PowerDistributionPanel pdp;
	public static Elevator elevator;
	public static Intake intake;
	public static Timer timer;
	public CameraServer server;

	public static OI oi;

    public static String gameData;

	Command autonomousCommand;
	SendableChooser chooser;


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		
		drivetrain = new DriveTrain();
		pdp = new PowerDistributionPanel(0);
		elevator = new Elevator();
		intake = new Intake();

		oi = new OI();

		pdp.clearStickyFaults();
		
		autonomousCommand = new AutonomousCommand();

		chooser = new SendableChooser();
		SmartDashboard.putData("Auto mode", chooser);
		server.getInstance().startAutomaticCapture(); //I THINK IT STARTS ON PORT 0
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit(){
		

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
        Timer.delay(0.005);
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (autonomousCommand != null) autonomousCommand.start();
    }

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();

		Scheduler.getInstance().removeAll();
	}

	/**
	 * This function is called periodically during o	perato'r control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		
	}

	public void log() {
		SmartDashboard.putString("DB/String 0", "Speed (L): " + String.format( "%.2f", (drivetrain.getLeftSpeed() * 100)) + "%");
		SmartDashboard.putString("DB/String 5", "Speed (R): " + String.format( "%.2f", (drivetrain.getRightSpeed() * 100)) + "%");
		SmartDashboard.putString("DB/String 1", "Factor: " + String.format( "%.2f", drivetrain.factor));
		Timer.delay(0.05);
	}
}
