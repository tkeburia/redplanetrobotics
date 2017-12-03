package com.tkeburia.redplanetrobotics.map;

import com.tkeburia.redplanetrobotics.exception.InputException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Cell {

    private int xCoordinate;
    private int yCoordinate;

    private final static int COORDINATE_MAX_VALUE = 50;

    public Cell(int xCoordinate, int yCoordinate) {
        if (xCoordinate > COORDINATE_MAX_VALUE ||  yCoordinate > COORDINATE_MAX_VALUE) {
            throw new InputException("Coordinate max value is 50!");
        }
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

}
