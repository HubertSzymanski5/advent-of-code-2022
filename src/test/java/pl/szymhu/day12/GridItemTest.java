package pl.szymhu.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GridItemTest {

    @Test
    void shouldPassAdventOfCodeExample() {
        var input = """
                Sabqponm
                abcryxxl
                accszExk
                acctuvwj
                abdefghi
                """;

        Hill hill = Hill.initialize(input);

        assertEquals(31, hill.getMinStepsToGetToEnd());
        assertEquals(29, hill.getMinStepsFromAnyASquare());
    }

}