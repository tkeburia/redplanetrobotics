package com.tkeburia.redplanetrobotics.map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Cell {
    private int xCoordinate;
    private int yCoordinate;
}
