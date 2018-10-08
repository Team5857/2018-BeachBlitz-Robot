package org.usfirst.frc.team5857.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hook extends Subsystem {
	
	Compressor compressor;
	DoubleSolenoid solenoid1;
	public static boolean sol1State; //true = forward, false = reverse
	
	public Hook()
	{
		compressor = new Compressor();
		solenoid1 = new DoubleSolenoid(3,4);
		sol1State = false;
		
		solenoid1.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void toggleCompressor()
	{
		if(compressor.enabled())
			compressor.stop();
		else
			compressor.start();
	}
	
	
	protected void initDefaultCommand() {}
	
	public void Pneumatic1Toggle()
	{
		if(solenoid1.get() == DoubleSolenoid.Value.kReverse)
			solenoid1.set(DoubleSolenoid.Value.kForward);
		else solenoid1.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	public void Pneumatic2Toggle()
	{
		if(solenoid2.get() == DoubleSolenoid.Value.kReverse)
			solenoid2.set(DoubleSolenoid.Value.kForward);
		else solenoid2.set(DoubleSolenoid.Value.kReverse);
	}
	**/
	
	public void Pneumatic1FWD()
	{
		solenoid1.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	public void Pneumatic2FWD()
	{
		solenoid2.set(DoubleSolenoid.Value.kForward);
	}
	**/
	
	public void Pneumatic1REV()
	{
		solenoid1.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	public void Pneumatic2REV()
	{
		solenoid2.set(DoubleSolenoid.Value.kReverse);
	}
	**/
}
