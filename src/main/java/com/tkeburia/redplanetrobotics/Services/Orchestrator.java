package com.tkeburia.redplanetrobotics.Services;

import com.tkeburia.redplanetrobotics.exception.InputException;
import com.tkeburia.redplanetrobotics.map.Cell;
import com.tkeburia.redplanetrobotics.map.Grid;
import com.tkeburia.redplanetrobotics.robot.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Orchestrator {

    private static final Logger LOG = LoggerFactory.getLogger(Orchestrator.class);

    private final ScannerService scannerService;
    private final GridService gridService;
    private final CommandService commandService;

    @Autowired
    public Orchestrator(ScannerService scannerService, GridService gridService, CommandService commandService) {
        this.scannerService = scannerService;
        this.gridService = gridService;
        this.commandService = commandService;
    }

    public void orchestrate() {
        final Grid gridWithBoundary = gridService.createGridWithBoundary(readUpperRightCellPosition());
        while (true) {
            readAndExecuteCommand(gridWithBoundary);
        }
    }

    private Cell readUpperRightCellPosition(){
        LOG.info("PLEASE ENTER THE UPPER RIGHT CELL COORDINATES");
        try {
            return scannerService.readCellPosition();
        } catch (InputException e) {
            LOG.error(e.getMessage());
            return readUpperRightCellPosition();
        }
    }

    private void readAndExecuteCommand(Grid grid) {
        final Robot robot = readAndExecuteRobotCreateCommand();
        readAndExecuteMoveCommand(robot, grid);
    }

    private void readAndExecuteMoveCommand(Robot robot, Grid grid) {
        LOG.info("PLEASE ENTER THE COMMAND FOR THE ROBOT TO FOLLOW");
        final List<String> commands = scannerService.readMoveCommand();
        final Robot resultRobot = commandService.executeCommandList(robot, commands, grid);
        LOG.info(String.format("NEW POSITION OF ROBOT [X Y Direction] %s", resultRobot));
    }

    private Robot readAndExecuteRobotCreateCommand() {
        LOG.info("PLEASE ENTER ROBOT STARTING POSITION AND ORIENTATION");
        try {
            return scannerService.readRobotPosition();
        } catch (InputException e) {
            LOG.error(e.getMessage());
            return readAndExecuteRobotCreateCommand();
        }
    }
}
