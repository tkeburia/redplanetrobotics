package com.tkeburia.redplanetrobotics.command;

import com.tkeburia.redplanetrobotics.map.Cell;
import com.tkeburia.redplanetrobotics.robot.Robot;
import org.junit.Test;

import static com.tkeburia.redplanetrobotics.map.Direction.*;
import static org.junit.Assert.*;

public class CommandTest {

    @Test
    public void shouldMoveNorthWithFCommandWhenFacingNorth() {
        Robot.Position pos = new Robot.Position(new Cell(1, 1), N);
        final Robot.Position result = Command.F.getMoveFunction().apply(pos);
        assertEquals(new Cell(1, 2), result.getCell());
        assertEquals(N, result.getDirection());
    }

    @Test
    public void shouldMoveEasthWithFCommandWhenFacingEast() {
        Robot.Position pos = new Robot.Position(new Cell(1, 1), E);
        final Robot.Position result = Command.F.getMoveFunction().apply(pos);
        assertEquals(new Cell(2, 1), result.getCell());
        assertEquals(E, result.getDirection());
    }

    @Test
    public void shouldMoveSouthhWithFCommandWhenFacingSouth() {
        Robot.Position pos = new Robot.Position(new Cell(1, 1), S);
        final Robot.Position result = Command.F.getMoveFunction().apply(pos);
        assertEquals(new Cell(1, 0), result.getCell());
        assertEquals(S, result.getDirection());
    }

    @Test
    public void shouldMoveWesthWithFCommandWhenFacingWest() {
        Robot.Position pos = new Robot.Position(new Cell(1, 1), W);
        final Robot.Position result = Command.F.getMoveFunction().apply(pos);
        assertEquals(new Cell(0, 1), result.getCell());
        assertEquals(W, result.getDirection());
    }

    @Test
    public void shouldFaceWestWithLCommandWhenFacingNorth() {
        Robot.Position pos = new Robot.Position(new Cell(1, 1), N);
        final Robot.Position result = Command.L.getMoveFunction().apply(pos);
        assertEquals(new Cell(1, 1), result.getCell());
        assertEquals(W, result.getDirection());
    }

    @Test
    public void shouldFaceWestWithLCommand4TimesWhenFacingWest() {
        Robot.Position pos = new Robot.Position(new Cell(1, 1), W);
        for (int i = 0; i <= 3; i++) {
            pos = Command.L.getMoveFunction().apply(pos);
        }
        assertEquals(new Cell(1, 1), pos.getCell());
        assertEquals(W, pos.getDirection());
    }

    @Test
    public void shouldFaceWestWithRCommandWhenFacingSouth() {
        Robot.Position pos = new Robot.Position(new Cell(1, 1), S);
        final Robot.Position result = Command.R.getMoveFunction().apply(pos);
        assertEquals(new Cell(1, 1), result.getCell());
        assertEquals(W, result.getDirection());
    }

    @Test
    public void shouldFaceEastWithRCommand4TimesWhenFacingWest() {
        Robot.Position pos = new Robot.Position(new Cell(1, 1), E);
        for (int i = 0; i <= 3; i++) {
            pos = Command.R.getMoveFunction().apply(pos);
        }
        assertEquals(new Cell(1, 1), pos.getCell());
        assertEquals(E, pos.getDirection());
    }


}