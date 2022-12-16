package pl.szymhu.day14;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SandVesselTest {

    @Test
    void shouldInitialize() {
        List<String> inputs = List.of(
                "498,4 -> 498,6 -> 496,6",
                "503,4 -> 502,4 -> 502,9 -> 494,9");

        SandVessel sandVessel = SandVessel.initialize(inputs);
        assertEquals(24, sandVessel.pourSand());
    }
}