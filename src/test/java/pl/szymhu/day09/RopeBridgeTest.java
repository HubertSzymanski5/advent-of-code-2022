package pl.szymhu.day09;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RopeBridgeTest {

    @Test
    void shouldPassAdventOfCodeExamplePartI() {
        List<String> inputs = List.of(
                "R 4",
                "U 4",
                "L 3",
                "D 1",
                "R 4",
                "D 1",
                "L 5",
                "R 2");

        RopeBridge ropeBridge = RopeBridge.initialize(inputs, 2);
        ropeBridge.executeCommands();

        assertEquals(13, ropeBridge.getUniqueTailPositionsNum());
    }

    @Test
    void shouldPassAdventOfCodeExamplePartII() {
        List<String> inputs = List.of(
                "R 5",
                "U 8",
                "L 8",
                "D 3",
                "R 17",
                "D 10",
                "L 25",
                "U 20");

        RopeBridge ropeBridge = RopeBridge.initialize(inputs, 10);
        ropeBridge.executeCommands();

        assertEquals(36, ropeBridge.getUniqueTailPositionsNum());
    }
}