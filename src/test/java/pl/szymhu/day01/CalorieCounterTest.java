package pl.szymhu.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalorieCounterTest {

    @Test
    void shouldPassAdventOfCodeExample() {
        var input = List.of(
                "1000\n2000\n3000",
                "4000",
                "5000\n6000",
                "7000\n8000\n9000",
                "10000");

        var counter = CalorieCounter.from(input);

        assertEquals(24000, counter.findMostCalories());
        assertEquals(45000, counter.findSumOfMost3Calories());
    }
}