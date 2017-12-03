package com.tkeburia.redplanetrobotics.command.Function;

import com.google.common.collect.ImmutableMap;
import com.tkeburia.redplanetrobotics.map.Cell;
import com.tkeburia.redplanetrobotics.map.Direction;
import com.tkeburia.redplanetrobotics.robot.Robot;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.tkeburia.redplanetrobotics.map.Direction.*;

public class CommandFunctionProvider {

    // map each direction enum to an actual orientation of the movement that needs to be carried out
    // number of cells moved is parameterized in case we want to add commands that move more than one cell
    private static Map<Direction, BiFunction<Cell, Integer, Cell>> moveFunctionMappings = ImmutableMap.of(
            N,(c, i) -> new Cell(c.getXCoordinate(), c.getYCoordinate() + i),
            E,(c, i) -> new Cell(c.getXCoordinate() + i, c.getYCoordinate()),
            S,(c, i) -> new Cell(c.getXCoordinate(), c.getYCoordinate() - i),
            W,(c, i) -> new Cell(c.getXCoordinate() - i, c.getYCoordinate())
    );

    public static Function<Robot.Position, Robot.Position> fCommandFunction() {
        return position -> {
            Direction direction = position.getDirection();
            Cell currentCell = position.getCell();
            return new Robot.Position(moveFunctionMappings.get(direction).apply(currentCell, 1), direction);
        };
    }

    public static Function<Robot.Position, Robot.Position> lCommandFunction() {
        return position -> {
            final Direction direction = position.getDirection();
            final int newDirection = (4 + (direction.getValue() - 1)) % 4;
            return new Robot.Position(position.getCell(), Direction.fromInt(newDirection));
        };
    }

    public static Function<Robot.Position, Robot.Position> rCommandFunction(){
        return position -> {
            final Direction direction = position.getDirection();
            final int newDirection = (direction.getValue() + 1) % 4;
            return new Robot.Position(position.getCell(), Direction.fromInt(newDirection));
        };
    }
}
