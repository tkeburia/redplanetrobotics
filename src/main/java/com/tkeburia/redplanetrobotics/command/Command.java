package com.tkeburia.redplanetrobotics.command;

import com.tkeburia.redplanetrobotics.command.Function.CommandFunctionProvider;
import com.tkeburia.redplanetrobotics.robot.Robot;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public enum Command {
    //if new commands are added, we only need to add a new value here and map it to an actual function that carries out
    // desired actions
    L(CommandFunctionProvider.lCommandFunction()),
    R(CommandFunctionProvider.rCommandFunction()),
    F(CommandFunctionProvider.fCommandFunction());

    @Getter
    private Function<Robot.Position, Robot.Position> moveFunction;

    Command(Function<Robot.Position, Robot.Position> moveFunction) {
        this.moveFunction = moveFunction;
    }

    public static List<String> valuesAsStringList(){
        return Stream.of(values()).map(Enum::toString).collect(toList());
    }

    public static Command fromString(String input) {
        return Stream.of(Command.values())
                     .filter(a -> a.toString().equals(input))
                     .findFirst()
                     .orElse(null);
    }
}
