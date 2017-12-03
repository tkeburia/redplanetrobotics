package com.tkeburia.redplanetrobotics.Services;

import com.tkeburia.redplanetrobotics.command.Command;
import com.tkeburia.redplanetrobotics.exception.InputException;
import com.tkeburia.redplanetrobotics.map.Cell;
import com.tkeburia.redplanetrobotics.map.Direction;
import com.tkeburia.redplanetrobotics.robot.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Integer.valueOf;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class ScannerService {
    private static final Logger LOG = LoggerFactory.getLogger(ScannerService.class);

    private final Scanner scanner;

    @Autowired
    public ScannerService(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * reads coordinates on command line and returns corresponding cell
     * @return a Cell with the coordinates provided on command line
     */
    public Cell readCellPosition(){
        String[] parts = getCharactersWithPattern("[0-9]+ [0-9]+");
        List<Integer> coordinates = Stream.of(parts).map(Integer::valueOf).collect(toList());
        LOG.info("created new Cell {} {}", coordinates.get(0), coordinates.get(1));
        return new Cell(coordinates.get(0), coordinates.get(1));
    }

    /**
     * reads coordinates and direction from command line and returns corresponding robot
     * @return a robot that is based on the position provided on the command line
     */
    public Robot readRobotPosition() {
        String[] parts = getCharactersWithPattern("[0-9]+ [0-9]+ [NESW]");
        LOG.info("created new Robot {} {} {}", parts[0], parts[1], parts[2]) ;
        return new Robot(new Cell(valueOf(parts[0]), valueOf(parts[1])), Direction.valueOf(parts[2]));
    }

    /**
     * reads a string representation of commands, checks the length and validity of commands, then returns
     * a list of command strings
     * @return list of strings representing commands
     */
    public List<String> readMoveCommand() {
        String rawValue = scanner.nextLine();
        // the max number of commands must not be exceeded
        if (rawValue.length() > 100) {
            throw new InputException("The Command sequence is too long!");
        }
        // create a stream supplier so we can reuse it
        Supplier<Stream<String>> streamSupplier = () -> rawValue.chars().mapToObj(value -> String.valueOf((char) value));
        // check the command is present in the Command enums
        streamSupplier.get()
                .filter(character -> !Command.valuesAsStringList().contains(character))
                .findAny()
                .ifPresent(
                        character -> {
                            throw new InputException(format("Invalid movement command %s", character));
                        }
                );
        return streamSupplier.get().collect(toList());
    }

    private String[] getCharactersWithPattern(String argPattern) {
        // reads the input line and makes sure it conforms to requested format using regex
        String rawValue = scanner.nextLine();
        Pattern p = Pattern.compile(argPattern);
        Matcher m = p.matcher(rawValue.trim());
        if (!m.matches()) {
            throw new InputException("Arguments provided in wrong format!");
        }
        return rawValue.trim().split(" ");
    }
}
