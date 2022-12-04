package pl.szymhu.day04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SectorTest {

    private final Sector bigger = Sector.from("4-6");
    private final Sector smaller = Sector.from("6-6");

    @Test
    void shouldReturnTrueWhenContainsOther() {
        assertTrue(bigger.contains(smaller));
    }

    @Test
    void shouldReturnFalseWhenNotContainsOther() {
        assertFalse(smaller.contains(bigger));
    }

}