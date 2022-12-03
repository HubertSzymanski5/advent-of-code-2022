package pl.szymhu.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RucksackReorganizerTest {

    @Test
    void shouldPassAdventOfCodeExamplePartI() {
        var input = List.of(
                "vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg",
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw");

        var reorganizer = RucksackReorganizer.initialize(input);

        assertEquals(List.of(16, 38, 42, 22, 20, 19), reorganizer.getPrioritiesValues());
        assertEquals(157, reorganizer.getPrioritiesSum());
    }
}