package pl.szymhu.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RucksackTest {

    @Test
    void shouldInitializeRucksackWithString() {
        var input = "abcABC";

        Rucksack rucksack = Rucksack.initialize(input);

        var expected = List.of(
                'a', 'b', 'c', 'A', 'B', 'C');

        assertEquals(expected, rucksack.getItems());
    }

    @Test
    void shouldReturnCompartments() {
        var input = "abcABC";
        Rucksack rucksack = Rucksack.initialize(input);

        assertEquals(List.of('a', 'b', 'c'), rucksack.getFirstCompartment());
        assertEquals(List.of('A', 'B', 'C'), rucksack.getSecondCompartment());
    }

    @Test
    void shouldFindItemDuplicatedInBothCompartments() {
        var input = "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL";
        Rucksack rucksack = Rucksack.initialize(input);

        assertEquals('L', rucksack.getDuplicatedType());
    }

}