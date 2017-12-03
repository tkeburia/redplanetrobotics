package com.tkeburia.redplanetrobotics.Services;

import com.tkeburia.redplanetrobotics.map.Cell;
import com.tkeburia.redplanetrobotics.map.Grid;
import org.springframework.stereotype.Service;

@Service
public class GridService {

    /**
     * create a grid given a upper right boundary cell
     * @param boundaryCell
     * @return new grid with the given boundary cell
     */
    public Grid createGridWithBoundary(Cell boundaryCell) {
        return new Grid(boundaryCell);
    }

    /**
     * check if the given cell is outside the given grid
     * @param cell
     * @param grid
     * @return
     */
    public boolean isCellOutOfBounds(Cell cell, Grid grid) {
        return cell.getXCoordinate() > grid.getUpperRightCell().getXCoordinate()
                || cell.getYCoordinate() > grid.getUpperRightCell().getYCoordinate()
                || cell.getXCoordinate() < 0
                || cell.getYCoordinate() < 0;
    }
}
