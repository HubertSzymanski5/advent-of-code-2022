package pl.szymhu.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void shouldPassAdventOfCodeExamplePartII() {
        var input = List.of(
                "vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg",
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw");

        var reorganizer = RucksackReorganizer.initialize(input);

        assertEquals(70, reorganizer.getBadgesPrioritiesSum());
    }

    @Test
    void shouldFindBadgeForGroup() {
        var group = List.of(
                Rucksack.initialize("vJrwpWtwJgWrhcsFMMfFFhFp"),
                Rucksack.initialize("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"),
                Rucksack.initialize("PmmdzqPrVvPwwTWBwg"));

        assertEquals('r', RucksackReorganizer.getBadgeFor(group));
    }

    @Test
    void shouldThrowExceptionIfGroupIsNot3Elves() {
        assertThrows(RuntimeException.class, () -> RucksackReorganizer.getBadgeFor(List.of()));
    }
}