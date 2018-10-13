package org.usfirst.frc.team5857.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5857.robot.Robot;

public class AutonomousCommand extends CommandGroup {
    public AutonomousCommand() {
        //addSequential(new AUTO_FWD());
        
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0) {
            Robot.elevator.resetEncoder();
            if(gameData.charAt(1) == 'R') {
                addSequential(new AUTO_LOCK_INTAKE());
                addSequential(new AUTO_LONG_FWD());
                addSequential(new AUTO_TURN());
                addSequential(new AUTO_LONG_UP());
                addSequential(new AUTO_RELEASE_INTAKE());
            }
            else if(gameData.charAt(0) == 'R') {
                addSequential(new AUTO_LOCK_INTAKE());
                addSequential(new AUTO_SHORT_UP());
                addSequential(new AUTO_SHORT_FWD());
                addSequential(new AUTO_TURN());
//                addSequential(new AUTO_SHORT_UP());
//                addSequential(new AUTO_RELEASE_INTAKE());
            }
            else if(gameData.charAt(0) == 'L') {
                addSequential(new AUTO_LOCK_INTAKE());
                addSequential(new AUTO_SHORT_FWD());
                addSequential(new AUTO_TURN());
                addSequential(new AUTO_LONG_FWD());
                addSequential(new AUTO_TURN());
                addSequential(new AUTO_SHORTSHORT_FWD());
                addSequential(new AUTO_TURN());
                addSequential(new AUTO_SHORT_UP());
                addSequential(new AUTO_RELEASE_INTAKE());
            }
        }
    }
}
           
