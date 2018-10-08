package org.usfirst.frc.team5857.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5857.robot.Robot;

public class AutonomousCommand extends CommandGroup {
    public AutonomousCommand() {
        //addSequential(new AUTO_FWD());
        
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (gameData.length() > 0) {
            if (gameData.charAt(1) == 'R') {

                System.out.print("execute right");
                //addSequential(new AUTO_FWD());
                //addSequential(new AUTO_TURN());
                //addSequential(new AUTO_SHOOTSCALE());
                //addSequential(new AUTO_SHOOT());


           // } else if (gameData.charAt(1) == 'R'){
            }
        }
    }
}
           
