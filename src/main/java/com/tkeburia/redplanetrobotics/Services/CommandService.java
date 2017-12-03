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


    public Robot executeCommandList(Robot inputRobot, List<String> commands, Grid grid) {
        return null;
    }
}