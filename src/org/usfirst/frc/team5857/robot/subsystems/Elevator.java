package org.usfirst.frc.team5857.robot.subsystems;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team5857.robot.commands.ToggleElevator;

import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Elevator extends Subsystem {

	public int count;
//	public Map <String, Integer> testControlMap1, testControlMap2;
	public String controlMode;
	public double minValue, maxValue;
	public static SpeedController elevator1, elevator2;

	public Elevator() {
		//this.resetEncoder();
		minValue = 0;
		maxValue = 70000;
		elevator1 = new WPI_TalonSRX(14);
		elevator2 = new WPI_TalonSRX(0);
		controlMode = "competition_mode";

//		testControlMap1 = new TreeMap <String, Object>();
//		testControlMap1.put("count", 0);
//		testControlMap1.put("minValue", Integer.MAX_VALUE);
//		testControlMap1.put("maxValue", Integer.MIN_VALUE);
	}

	public void toggleElevator(Joystick secondaryStick, Joystick driveStick) {
		/* secondaryStick:
		 * rawAxis 5: move elevator up/down
		 * Y: max height
		 * X: bottom
		 * B: switch height
		 * Trigger: Closes/locks intake
		 * 
		 * driveStick
		 * trigger: open intake all the way
		 * 2: open 10 ms (loosens but not too much)
		 */
		if(controlMode.equals("competition_mode")) {
			int baseDirection = 10;
			double speed = driveStick.getRawAxis(3) - driveStick.getRawAxis(2);
			if(driveStick.getRawButtonPressed(8)) {
				resetEncoder();
			}
			elevator1.set(speed);
			elevator2.set(-speed);
			

			if(((BaseMotorController) elevator2).getSelectedSensorPosition(0) < 40000) {
				baseDirection = 1;
			}

			else if(((BaseMotorController) elevator2).getSelectedSensorPosition(0) == 40000) {
				baseDirection = 0;
			}

			else if(((BaseMotorController) elevator2).getSelectedSensorPosition(0) > 40000) {
				baseDirection = -1;
			}

			if(driveStick.getRawButtonPressed(1)) {
				if(baseDirection == 1) {
					while(((BaseMotorController) elevator2).getSelectedSensorPosition(0) < 40000) {
						elevator1.set(1);
						elevator2.set(-1);
						if(driveStick.getRawButtonPressed(4)) {
							elevator1.set(0);
							elevator2.set(0);
						}
					}
				}
				else if(baseDirection == -1) {
					while(((BaseMotorController) elevator2).getSelectedSensorPosition(0) > 40000) {
						elevator1.set(-1);
						elevator2.set(1);
						if(driveStick.getRawButtonPressed(4)) {
							elevator1.set(0);
							elevator2.set(0);
						}
					}
				}
				elevator1.set(0);
				elevator2.set(0);
			}

			if(driveStick.getRawButtonPressed(2)) {
				if(baseDirection == 1 || baseDirection == 0) {
					while(((BaseMotorController) elevator2).getSelectedSensorPosition(0) < 70000) {
						elevator1.set(1);
						elevator2.set(-1);
						if(driveStick.getRawButtonPressed(4)) {
							elevator1.set(0);
							elevator2.set(0);
						}
					}
				}
				elevator1.set(0);
				elevator2.set(0);
			}
			
			if(driveStick.getRawButton(3)) {
				if(baseDirection == 0 || baseDirection == -1) {
					while(((BaseMotorController) elevator2).getSelectedSensorPosition(0) > 0) {
						elevator1.set(-0.5);
						elevator2.set(0.5);
						if(driveStick.getRawButtonPressed(4)) {
							elevator1.set(0);
							elevator2.set(0);
						}
					}
					elevator1.set(0);
					elevator2.set(0);
				}
			}
		}
	}
	
	public void resetEncoder() {
		((BaseMotorController) elevator2).setSelectedSensorPosition(0,  0,  10);
		System.out.println("Encoder has been reset");
	}
	
	public boolean hasResetOccurred() {
		return ((BaseMotorController) elevator1).getSelectedSensorPosition(0) == 0;
	}
	
	/*public boolean getElevator1Speed() {
		return elevator.getInverted();
	}*/
	public double getElevator1Speed() {
		return elevator1.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ToggleElevator());
	}


}