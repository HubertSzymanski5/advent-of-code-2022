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

        RopeBridge ropeBridge = RopeBridge.initialize(inputs);
        ropeBridge.executeCommands();

        assertEquals(13, ropeBridge.getUniqueTailPositionsNum());
    }
}