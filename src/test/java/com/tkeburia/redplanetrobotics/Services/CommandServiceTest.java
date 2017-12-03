package com.tkeburia.redplanetrobotics.Services;

import com.google.common.collect.ImmutableList;
import com.tkeburia.redplanetrobotics.map.Cell;
import com.tkeburia.redplanetrobotics.map.Grid;
import com.tkeburia.redplanetrobotics.robot.Robot;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.tkeburia.redplanetrobotics.map.Direction.*;
import static org.junit.Assert.*;

public class CommandServiceTest {

    private CommandService commandService;
    private final static Grid grid = new Grid(new Cell(5, 3));

    @Before
    public void setup() {
        commandService = new CommandService(new GridService());
    }

    @Test
    public void shouldReturnToStartingPosition() {
        Robot robot = new Robot(new Cell(1, 1), E);
        List<String> commands = ImmutableList.of("R", "F", "R", "F", "R", "F", "R", "F");
        final Robot result = commandService.executeCommandList(robot, commands, grid);

        assertEquals(new Cell(1, 1), result.getCurrentPosition().getCell());
        assertEquals(E, result.getCurrentPosition().getDirection());
        assertFalse(result.isLost());
    }

    @Test
    public void shouldBeLostAndLeaveScent() {
        Robot robot = new Robot(new Cell(3, 2), N);
        List<String> commands = ImmutableList.of("F", "R", "R", "F", "L", "L", "F", "F", "R", "R", "F", "L", "L");
        final Robot result = commandService.executeCommandList(robot, commands, grid);

        assertEquals(new Cell(3, 3), result.getCurrentPosition().getCell());
        assertEquals(N, result.getCurrentPosition().getDirection());
        assertTrue(result.isLost());
    }

    @Test
    public void shouldAvoidGettingLostWhereTheScentIsLeft() {
        Robot robot = new Robot(new Cell(0, 3), W);
        List<String> commands = ImmutableList.of("L", "L", "F", "F", "F", "L", "F", "L", "F", "L");
        final Robot result = commandService.executeCommandList(robot, commands, grid);

        assertEquals(new Cell(2, 3), result.getCurrentPosition().getCell());
        assertEquals(S, result.getCurrentPosition().getDirection());
        assertFalse(result.isLost());
    }



}