package com.tkeburia.redplanetrobotics.Services;

import com.tkeburia.redplanetrobotics.exception.InputException;
import com.tkeburia.redplanetrobotics.map.Cell;
import com.tkeburia.redplanetrobotics.robot.Robot;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ScannerServiceTest {

    private ScannerService scannerService;

    @Test
    public void shouldReturnNewCellWhenCorrectCoordinatesAreProvided() throws Exception {
        String input = "1 3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        scannerService = new ScannerService(new Scanner(System.in));
        final Cell cell = scannerService.readCellPosition();

        assertEquals(1, cell.getXCoordinate());
        assertEquals(3, cell.getYCoordinate());
    }

    @Test(expected = InputException.class)
    public void shouldThrowExceptionForInvalidInput() throws Exception {
        String input = "1 X";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        scannerService = new ScannerService(new Scanner(System.in));
        scannerService.readCellPosition();
    }

    @Test
    public void shouldReturnRobotInstanceWhenCorrectArgsAreProvided() throws Exception {
        String input = "1 3 E";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        scannerService = new ScannerService(new Scanner(System.in));
        final Robot robot = scannerService.readRobotPosition();

        assertEquals(1, robot.getCurrentPosition().getCell().getXCoordinate());
        assertEquals(3, robot.getCurrentPosition().getCell().getYCoordinate());
        assertEquals("E", robot.getCurrentPosition().getDirection().toString());
    }

    @Test(expected = InputException.class)
    public void shouldThrowExceptionForInvalidInputForRobotPosition() {
        String input = "Y X";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        scannerService = new ScannerService(new Scanner(System.in));
        scannerService.readRobotPosition();
    }

    @Test
    public void shouldReturnListOfCommandsWhenCorrectArgsAreProvided() throws Exception {
        String input = "FRLLF";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        scannerService = new ScannerService(new Scanner(System.in));
        final List<String> strings = scannerService.readMoveCommand();

        assertThat(strings, Matchers.contains("F", "R", "L", "L", "F"));
    }

    @Test(expected = InputException.class)
    public void shouldThrowExceptionWhenCommandStringTooLong() {
        String input = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        scannerService = new ScannerService(new Scanner(System.in));
        scannerService.readMoveCommand();

    }

    @Test(expected = InputException.class)
    public void shouldThrowExceptionWhenInvalidCommandIsSupplied() {
        String input = "DDD";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        scannerService = new ScannerService(new Scanner(System.in));
        scannerService.readMoveCommand();

    }


}