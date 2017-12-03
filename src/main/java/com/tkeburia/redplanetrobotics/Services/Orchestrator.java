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
        return null;
    }

    private void readAndExecuteCommand(Grid grid) {
        final Robot robot = readAndExecuteRobotCreateCommand();
        readAndExecuteMoveCommand(robot, grid);
    }

    private void readAndExecuteMoveCommand(Robot robot, Grid grid) {
    }

    private Robot readAndExecuteRobotCreateCommand() {
        return null;
    }
}
