package pl.szymhu.day04;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CampCleanerTest {

    @Test
    void shouldPassAdventOfCodeExamplePartI() {
        var input = List.of(
                "2-4,6-8",
                "2-3,4-5",
                "5-7,7-9",
                "2-8,3-7",
                "6-6,4-6",
                "2-6,4-8");
        var cleaner = CampCleaner.initialize(input);
        assertEquals(2, cleaner.getSumOfFullyContainedInTheOther());
    }
}