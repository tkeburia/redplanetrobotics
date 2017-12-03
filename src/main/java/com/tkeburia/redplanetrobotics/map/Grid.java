package com.tkeburia.redplanetrobotics.map;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Grid {
    private final Cell upperRightCell;
    private final Set<Cell> cellsWithScent;

    public Grid(Cell upperRightCell) {
        this.upperRightCell = upperRightCell;
        // Always initialize this as empty since we have no cells with scent for a fresh grid
        this.cellsWithScent = new HashSet<>();
    }
}
