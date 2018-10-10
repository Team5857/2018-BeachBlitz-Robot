package org.usfirst.frc.team5857.robot.subsystems;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team5857.robot.commands.ToggleElevator;
import org.usfirst.frc.team5857.robot.commands.ToggleElevatorUp;

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
	public Map <String, Integer> testControlMap1, testControlMap2;
	public String controlMode;
	public double minValue, maxValue;
	public static SpeedController elevator1, elevator2;
	
	public Elevator() {
		resetEncoder();
		minValue = 0;
		maxValue = 40000;
		elevator1 = new WPI_TalonSRX(14);
		elevator2 = new WPI_TalonSRX(0);
		controlMode = "competition_mode";

		testControlMap1 = new TreeMap <String, Object>();
		testControlMap1.put("count", 0);
		testControlMap1.put("minValue", Integer.MAX_VALUE);
		testControlMap1.put("maxValue", Integer.MIN_VALUE);

	}

	
	public void toggleElevator(Joystick secondaryStick, Joystick driveStick) {
		if(driveStick.getRawButtonPresed(7)) {
			controlMode = "competition_mode";
			resetEncoder();
		}
		if(driveStick.getRawButtonPresed(8)) {
			controlMode = "test_control_1";
			resetEncoder();
		}
		if(driveStick.getRawButtonPresed(9)) {
			controlMode = "test_control_2";
			resetEncoder();
		}
		if(driveStick.getRawButtonPresed(10)) {
			controlMode = "fix_mode";
			resetEncoder();
		}
		
		// @mode competition_mode: control mode for competition
		if(controlMode.equals("competition_mode")) {
			int baseDirection;
			double originalSpeed = secondaryStick.getRawAxis(5);
			if(secondaryStick.getRawButtonPressed(4)) {
				resetEncoder();
			}

			if(((BaseMotorController) elevator2).getSelectedSensorPosition(0) >= testControlMap1.get("minValue") && numRotations <= testControlMap1.get("maxValue")) {
				elevator1.set(originalSpeed);
				elevator2.set(-originalSpeed);
			}

			if(((BaseMotorController) elevator2).getSelectedSensorPosition(0) < 21310) {
				baseDirection = 1;
			}
			else if(((BaseMotorController) elevator2).getSelectedSensorPosition(0) == 21310) {
				baseDirection = 0;
			}
			else if(((BaseMotorController) elevator2).getSelectedSensorPosition(0) > 21310) {
				baseDirection = -1;
			}
			if(secondaryStick.getRawButtonPressed(3)) {

				while(((BaseMotorController) elevator2).getSelectedSensorPosition(0) < 21310) {
					elevator1.set(baseDirection*1);
					elevator2.set(baseDirection*1);
					if(driveStick.getRawButtonPressed(7)) {
						elevator1.set(0);
						elevator2.set(0);
					}
				}
				elevator1.set(0);
				elevator2.set(0);
			}
			if(secondaryStick.getRawButtonPressed(4)) {
				while(((BaseMotorController) elevator2).getSelectedSensorPosition(0) < 40000) {
					elevator1.set(baseDirection*1);
					elevator2.set(baseDirection*1);
					if(driveStick.getRawButtonPressed(7)) {
						elevator1.set(0);
						elevator2.set(0);
					}
				}
				elevator1.set(0);
				elevator2.set(0);
			}
			if(driveStick.getRawButton(2)) {
				while(((BaseMotorController) elevator2).getSelectedSensorPosition(0) > 0) {
					elevator1.set(-1 * baseDirection * 0.5);
					elevator2.set(baseDirection * 0.5);
					if(driveStick.getRawButtonPressed(7)) {
						elevator1.set(0);
						elevator2.set(0);
					}
				}
				elevator1.set(0);
				elevator2.set(0);
			}

		}
		// @mode test_control_1: testing mode for elevator values
		else if(controlMode.equals("test_control_1")) {
			double originalSpeed = secondaryStick.getRawAxis(5);
			if(secondaryStick.getRawButtonPressed(4)) {
				resetEncoder();
			}
			elevator1.set(originalSpeed);
			elevator2.set(-originalSpeed);

			if(((BaseMotorController) elevator2).getSelectedSensorPosition(0) < minValue) {
				testControlMap1.set("minValue", ((BaseMotorController) elevator2).getSelectedSensorPosition(0));
				//minValue = ((BaseMotorController) elevator2).getSelectedSensorPosition(0);
			}
			if(((BaseMotorController) elevator2).getSelectedSensorPosition(0) > maxValue) {
				testControlMap1.set("maxValue", ((BaseMotorController) elevator2).getSelectedSensorPosition(0));
				//maxValue = ((BaseMotorController) elevator2).getSelectedSensorPosition(0);
			}

			if(secondaryStick.getRawButtonPressed(3)) {
				testControlMap1.put("Breakpoint " + Integer.toString(testControlMap1.get("count")), ((BaseMotorController) elevator2).getSelectedSensorPosition(0));
				testControlMap1.set("count", testControlMap1.get("count")+1);
			}

			SmartDashboard.putNumber("Min Value", minValue);
			SmartDashboard.putNumber("Max Value", maxValue);
			SmartDashboard.putNumber("Current Value", ((BaseMotorController) elevator2).getSelectedSensorPosition(0));

			if(secondaryStick.getRawButtonPressed(2)) {
				Iterator<String> iter1 = testControlMap1.keySet();
				while(iter1.hasNext()) {
					String key = iter1.next();
					SmartDashboard.putNumber(key, testControlMap1.get(key));
				}
			}
		}
		else if(controlMode.equals("test_control_2")) {

		}
	}
	
	public void resetEncoder() {
		((BaseMotorController) elevator2).setSelectedSensorPosition(0,  0,  10);
		System.out.print("Encoder has been reset");
	}
	
	public boolean hasResetOccurred() {
		return ((BaseMotorController) elevator1).getSelectedSensorPosition(0) == 0;
	}
	
	public double getElevator1Speed() {
		return elevator1.get();
	}

	public double getElevator2Speed() {
		return elevator2.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ToggleElevator());
	}
	
}