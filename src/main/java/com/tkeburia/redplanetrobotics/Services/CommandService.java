package com.tkeburia.redplanetrobotics.Services;

import com.tkeburia.redplanetrobotics.command.Command;
import com.tkeburia.redplanetrobotics.map.Grid;
import com.tkeburia.redplanetrobotics.robot.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandService {

    private final GridService gridService;

    @Autowired
    public CommandService(GridService gridService) {
        this.gridService = gridService;
    }


    /**
     * executes the list of commands for given robot on given grid
     * @param inputRobot
     * @param commands
     * @param grid
     * @return a robot with the resulting position after executing the command list
     */
    public Robot executeCommandList(Robot inputRobot, List<String> commands, Grid grid) {
        Robot.Position currentPosition = inputRobot.getCurrentPosition();
        boolean robotLost = false;
        for (String c : commands) {
            // we keep track of both current and next cell to determine when the movement will take us off the grid
            Robot.Position nextPosition = Command.fromString(c).getMoveFunction().apply(currentPosition);
            if (gridService.isCellOutOfBounds(nextPosition.getCell(), grid)) {
                // if the next cell takes us off the grid and another robot has been here ignore the current command
                if (grid.getCellsWithScent().contains(currentPosition.getCell())) {
                    continue;
                }
                // if this is the first robot to go off grid here, set the lost flag and add the scent to the grid
                robotLost = true;
                grid.getCellsWithScent().add(currentPosition.getCell());
                break;
            }
            // if next position is still within the grid, we can move there and make it current
            currentPosition = nextPosition;
        }
        return new Robot(currentPosition, robotLost);
    }
}
