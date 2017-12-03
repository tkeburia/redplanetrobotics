package com.tkeburia.redplanetrobotics.map;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public enum Direction {
    N(0),
    E(1),
    S(2),
    W(3);

    @Getter
    private int value;

    Direction(int value) {
        this.value = value;
    }

    public static Direction fromInt(int inputInt) {
        return Stream.of(Direction.values())
                     .filter(a -> a.getValue() == inputInt)
                     .findFirst()
                     .orElse(null);
    }

}
