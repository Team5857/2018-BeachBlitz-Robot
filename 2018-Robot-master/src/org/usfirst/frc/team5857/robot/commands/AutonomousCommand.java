package org.usfirst.frc.team5857.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5857.robot.Robot;

public class AutonomousCommand extends CommandGroup {
    public AutonomousCommand() {
        addSequential(new AUTO_FWD());
        /*
        //USE WHEN YALL ARE READY
        if (Robot.gameData.length() > 0) {
            if (Robot.gameData.charAt(0) == 'L') {

                //Put left auto code here

            } else {

                //Put right auto code here

            }
        }
        */
    }
}
