package com.tkeburia.redplanetrobotics.Services;

import com.tkeburia.redplanetrobotics.map.Cell;
import com.tkeburia.redplanetrobotics.map.Grid;
import org.springframework.stereotype.Service;

@Service
public class GridService {

    public Grid createGridWithBoundary(Cell boundaryCell) {
        return new Grid(boundaryCell);
    }

    public boolean isCellOutOfBounds(Cell cell, Grid grid) {
        return cell.getXCoordinate() > grid.getUpperRightCell().getXCoordinate()
                || cell.getYCoordinate() > grid.getUpperRightCell().getYCoordinate()
                || cell.getXCoordinate() < 0
                || cell.getYCoordinate() < 0;
    }
}
