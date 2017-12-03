package com.tkeburia.redplanetrobotics.Services;

import com.tkeburia.redplanetrobotics.map.Cell;
import com.tkeburia.redplanetrobotics.map.Grid;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridServiceTest {

    private GridService gridService;

    @Before
    public void setup() {
        gridService = new GridService();
    }

    @Test
    public void shouldReturnACorrectGrid() {
        final Grid gridWithBoundary = gridService.createGridWithBoundary(new Cell(13, 27));
        assertEquals(13, gridWithBoundary.getUpperRightCell().getXCoordinate());
        assertEquals(27, gridWithBoundary.getUpperRightCell().getYCoordinate());
    }

    @Test
    public void shouldReturnFalseWhenCellNotOutOfBounds() {
        final Grid gridWithBoundary = gridService.createGridWithBoundary(new Cell(5, 5));
        final Cell insideBoundsCell = new Cell(1, 1);
        assertFalse(gridService.isCellOutOfBounds(insideBoundsCell, gridWithBoundary));
    }

    @Test
    public void shouldReturnTrueWhenCellOutOfBounds() {
        final Grid gridWithBoundary = gridService.createGridWithBoundary(new Cell(5, 5));
        final Cell insideBoundsCell = new Cell(10, 10);
        assertTrue(gridService.isCellOutOfBounds(insideBoundsCell, gridWithBoundary));
    }

    @Test
    public void shouldReturnTrueWhenCellWithNegativeCoordinates() {
        final Grid gridWithBoundary = gridService.createGridWithBoundary(new Cell(5, 5));
        final Cell insideBoundsCell = new Cell(-1, -1);
        assertTrue(gridService.isCellOutOfBounds(insideBoundsCell, gridWithBoundary));
    }
}