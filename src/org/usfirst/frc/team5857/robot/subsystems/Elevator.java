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
	public static double FORCE_CONSTANT = 3;
	public double runTime;
	public int direction;
	public String controlMode, elevatorState;
	public static SpeedController elevator1, elevator2;
	public static Encoder encoder;
	public double maxValue;
	
	public Elevator() {
		elevator1 = new WPI_TalonSRX(14);
		elevator2 = new WPI_TalonSRX(0);
		encoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		runTime = 0;
		direction = 0;
		elevatorState = "ground";
		controlMode = "test_control_5";
		maxValue = 0;
	}

	public void toggleElevatorUp(Joystick secondaryStick) {
		/*if(secondaryStick.getRawButton(3)) {
			elevator.set(-1.0);
			Timer.delay(5);
			elevator.set(-0.4);
			Timer.delay(10);
			
		} else if(!secondaryStick.getRawButton(4)){
			elevator.set(-0.15);
		}*/
		elevator1.set(secondaryStick.getRawAxis(0));
		elevator2.set(-secondaryStick.getRawAxis(0));
	}

	public void toggleElevatorDown(Joystick secondaryStick) {

//		if(secondaryStick.getRawButton(4)) {
//		elevator.set(0.5);
//	} else if(!secondaryStick.getRawButton(3)){
//		elevator.set(-0.15);
//	}			
	}
	
	// Button 7: ground state
	// Button 8: middle state
	// Button 9: top state
	public void updateElevator(Joystick secondaryStick) {
		if(elevatorState.equals("ground")) {
			if(secondaryStick.getRawButton(7)) {
				runTime = 0;
				direction = 0;
			}
			else if(secondaryStick.getRawButton(8)) {
				runTime = calculateRunTime();
				direction = 1;
				//elevatorState = "middle";
			}
			else if(secondaryStick.getRawButton(9)) {
				runTime = calculateRunTime();
				direction = 1;
				//elevatorState = "top";
			}
		}
		else if(elevatorState.equals("middle")) {
			if(secondaryStick.getRawButton(7)) {
				runTime = calculateRunTime();
				direction = -1;
				//elevatorState = "ground";
			}
			else if(secondaryStick.getRawButton(8)) {
				runTime = 0;
				direction = 0;
			}
			else if(secondaryStick.getRawButton(9)) {
				runTime = calculateRunTime();
				direction = 1;
				//elevatorState = "top";
			}
		}
		else if(elevatorState.equals("top")) {
			if(secondaryStick.getRawButton(7)) {
				runTime = calculateRunTime();
				direction = -1;
				//elevatorState = "ground";
			}
			else if(secondaryStick.getRawButton(8)) {
				runTime = calculateRunTime();
				direction = -1;
				//elevatorState = "middle";
			}
			else if(secondaryStick.getRawButton(9)) {
				runTime = 0;
				direction = 0;
			}
		}
	}
	
	public void toggleElevator(Joystick secondaryStick, Joystick driveStick) {
//		updateElevator(secondaryStick);
//		updateElevator(driveStick);
		if(controlMode.equals("user_defined")) {
			if(secondaryStick.getRawButtonPressed(4)) {
				elevator1.set(direction*0.15);
				elevator2.set(direction*0.15);
				Timer.delay(runTime);
				elevator1.set(0);
				elevator2.set(0);
			}
			else if(secondaryStick.getRawButtonPressed(3)) {
				elevator1.set(direction*0.15);
				elevator2.set(direction*0.15);
				Timer.delay(runTime);
				elevator1.set(0);
				elevator2.set(0);
			}
		}
		else if(controlMode.equals("fine_control")) {
			// TODO logarithmic scaling
			double originalSpeed = secondaryStick.getRawAxis(5);
			elevator1.set(originalSpeed);
			elevator2.set(-originalSpeed);

		}	
		else if(controlMode.equals("test_control_0")) {
//			resetEncoder();
			if(secondaryStick.getRawButton(2)) {
				resetEncoder();
			}
			if(secondaryStick.getRawButtonPressed(3)) {
				while(((BaseMotorController) elevator2).getSelectedSensorPosition(0) < 21310) {
					elevator1.set(1);
					elevator2.set(-1);
					// emergency stop
				}
				elevator1.set(0);
				elevator2.set(0);
			}
			if(secondaryStick.getRawButtonPressed(4)) {
				while(((BaseMotorController) elevator2).getSelectedSensorPosition(0) < 40000) {
					elevator1.set(1);
					elevator2.set(-1);
				}
				elevator1.set(0);
				elevator2.set(0);
			}
			if(driveStick.getRawButton(2)) {
				while(((BaseMotorController) elevator2).getSelectedSensorPosition(0) > 0) {
					elevator1.set(-0.3);
					elevator2.set(0.3);
					if(driveStick.getRawButtonPressed(7)) {
						elevator1.set(0);
						elevator2.set(0);
					}
				}
				elevator1.set(0);
				elevator2.set(0);
			}
			double originalSpeed = secondaryStick.getRawAxis(5);
			if(((BaseMotorController) elevator2).getSelectedSensorPosition(0) < 38000) {
				elevator1.set(originalSpeed);
				elevator2.set(-originalSpeed);
			}
		}
		else if(controlMode.equals("test_control_1")) {
			//System.out.println("Test Control Mode");
			double originalSpeed = secondaryStick.getRawAxis(5);
			elevator1.set(originalSpeed);
			elevator2.set(-originalSpeed);
			//B: print sensor position
			//X: reset encoder
			if(secondaryStick.getRawButtonPressed(2)) {
				System.out.println(((BaseMotorController) elevator2).getSelectedSensorPosition(0));
			}
			if(secondaryStick.getRawButtonPressed(3)) {
				resetEncoder();
			}
		}
		else if(controlMode.equals("test_control_2")) {
			if(secondaryStick.getRawButtonPressed(3)) {
				elevator1.set(0.50);
				Timer.delay(0.25);
				elevator1.set(0);
				elevator2.set(-0.50);
				Timer.delay(0.25);
				elevator2.set(0);
			}
			double originalSpeed = secondaryStick.getRawAxis(5);
			elevator1.set(originalSpeed);
			elevator2.set(-originalSpeed);
		}
		else if(controlMode.equals("test_control_3")) {
			resetEncoder();
			System.out.println(((BaseMotorController) elevator2).getSelectedSensorPosition(0));
			double speed = secondaryStick.getRawAxis(5);
			elevator1.set(speed);
			elevator2.set(-speed);
			System.out.println(((BaseMotorController) elevator2).getSelectedSensorPosition(0));
			if(secondaryStick.getRawButtonPressed(4)) {
				elevator1.set(0);
				elevator2.set(0);
			}
		}
		else if(controlMode.equals("test_control_4")) {
			if(secondaryStick.getRawButtonPressed(4)) {
				maxValue = 0;
				resetEncoder();
			}
			if(secondaryStick.getRawButtonPressed(3)) {
				SmartDashboard.putNumber("Max Height", maxValue);
				SmartDashboard.putNumber("Display Value", ((BaseMotorController) elevator2).getSelectedSensorPosition(0));
				
			}
			double originalSpeed = secondaryStick.getRawAxis(5);
			elevator1.set(originalSpeed);
			elevator2.set(-originalSpeed);
			if(((BaseMotorController) elevator2).getSelectedSensorPosition(0) >= maxValue) {
				maxValue = ((BaseMotorController) elevator2).getSelectedSensorPosition(0);
			}
		}
		else if(controlMode.equals("test_control_5")) {
			double speed = secondaryStick.getRawAxis(5);
			elevator1.set(speed);
			if(driveStick.getRawButtonPressed(6)) {
				elevator1.set(0.5);
				elevator2.set(-0.5);
				Timer.delay(0.25);
				elevator1.set(0);
				elevator2.set(0); 
			}
			if(driveStick.getRawButtonPressed(4)) {
				elevator1.set(-0.5);
				elevator2.set(0.5);
				Timer.delay(0.25);
				elevator1.set(0);
				elevator2.set(0);
			}
		}
	}
	
	public double calculateRunTime() {
		return 1;
	}
	
	public void resetEncoder() {
		((BaseMotorController) elevator2).setSelectedSensorPosition(0,  0,  10);
		System.out.print("Encoder has been reset");
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