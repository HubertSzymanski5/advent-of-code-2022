package pl.szymhu.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SectorTest {

    private final Sector bigger = Sector.from("4-6");
    private final Sector smaller = Sector.from("6-6");
    private final Sector overlappingEnd = Sector.from("3-5");
    private final Sector overlappingStart = Sector.from("5-7");
    private final Sector outside = Sector.from("1-2");
    @Test
    void shouldReturnTrueWhenContainsOther() {
        assertTrue(bigger.contains(smaller));
    }

    @Test
    void shouldReturnFalseWhenNotContainsOther() {
        assertFalse(smaller.contains(bigger));
    }

    @Test
    void shouldReturnTrueWhenOverlappingStart() {
        assertTrue(bigger.overlaps(overlappingStart));
        assertTrue(bigger.overlaps(smaller));
    }

    @Test
    void shouldReturnTrueWhenOverlappingEnd() {
        assertTrue(bigger.overlaps(overlappingEnd));
    }

    @Test
    void shouldReturnFalseWhenNotOverlapping() {
        assertFalse(bigger.overlaps(outside));
    }
}