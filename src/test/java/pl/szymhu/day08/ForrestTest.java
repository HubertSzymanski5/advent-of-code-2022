package pl.szymhu.day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForrestTest {

    @Test
    void shouldPassAdventOfCodeExample() {
        String input = """
                30373
                25512
                65332
                33549
                35390
                """;
        String expected = """
                [3][0][3][7][3]
                [2][5][5] 1 [2]
                [6][5] 3 [3][2]
                [3] 3 [5] 4 [9]
                [3][5][3][9][0]
                """;

        Forrest forrest = Forrest.initialize(input);

        assertEquals(expected, forrest.toString());
        assertEquals(21, forrest.countVisibleTrees());
        assertEquals(8, forrest.findBestTreeScenicScore());
    }
}