package com.tkeburia.redplanetrobotics.robot;

import com.tkeburia.redplanetrobotics.map.Cell;
import com.tkeburia.redplanetrobotics.map.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Robot {

    private final Position currentPosition;
    private boolean lost = false;

    public Robot(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Robot(Cell cell, Direction direction) {
        this(new Position(cell, direction));
    }

    @Data
    @AllArgsConstructor
    public static class Position {
        private Cell cell;
        private Direction direction;
    }


}
