package pl.szymhu.day05;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplyStacksTest {

    @Test
    void shouldInitializeStacksWithStringRepresentation() {
        var input = """
                    [D]
                [N] [C]
                [Z] [M] [P]
                 1   2   3
                """;

        var supply = SupplyStacks.initialize(input);

        assertIterableEquals(new ArrayDeque<>(List.of('Z', 'N')), supply.getStacks().get(0));
        assertIterableEquals(new ArrayDeque<>(List.of('M', 'C', 'D')), supply.getStacks().get(1));
        assertIterableEquals(new ArrayDeque<>(List.of('P')), supply.getStacks().get(2));
    }
}