package pl.szymhu.day13;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PairPacketTest {

    @Test
    void shouldReturnTrueWhenPackagesInOrderForIntList() {
        var input = List.of("[1,1,3,1,1]", "[1,1,5,1,1]");
        PairPacket pairPacket = PairPacket.from(input);
        assertTrue(pairPacket.isInOrder());
    }

    @Test
    void shouldReturnFalseWhenPackagesNotInOrderForIntList() {
        var input = List.of("[1,1,5,1,1]", "[1,1,3,1,1]");
        PairPacket pairPacket = PairPacket.from(input);
        assertFalse(pairPacket.isInOrder());
    }

    @Test
    void shouldReturnTrueWhenPackagesInOrderWithNestedLists() {
        var input = List.of("[1,[2,[3,[4,[5,6,0]]]],8,9]", "[1,[2,[3,[4,[5,6,7]]]],8,9]");
        PairPacket pairPacket = PairPacket.from(input);
        assertTrue(pairPacket.isInOrder());
    }

    @Test
    void shouldReturnFalseWhenPackagesNotInOrderWithNestedLists() {
        var input = List.of("[1,[2,[3,[4,[5,6,7]]]],8,9]", "[1,[2,[3,[4,[5,6,0]]]],8,9]");
        PairPacket pairPacket = PairPacket.from(input);
        assertFalse(pairPacket.isInOrder());
    }

    @Test
    void shouldReturnTrueIfFirstElementIsSmaller() {
        var input = List.of("[2,3,4]", "[4]");
        PairPacket pairPacket = PairPacket.from(input);
        assertTrue(pairPacket.isInOrder());
    }

    @Test
    void shouldReturnFalseIfRightRunsOutOfItems() {
        var input = List.of("[7,7,7]", "[7,7]");
        PairPacket pairPacket = PairPacket.from(input);
        assertFalse(pairPacket.isInOrder());
    }

    @Test
    void shouldReturnTrueIfMixedInRightOrder() {
        var input = List.of("[[1],[2,3,4]]", "[[1],4]");
        PairPacket pairPacket = PairPacket.from(input);
        assertTrue(pairPacket.isInOrder());
    }

    @Test
    void shouldReturnFalseIfMixedNotInRightOrder() {
        var input = List.of("[[1],4]", "[[1],[2,3,4]]");
        PairPacket pairPacket = PairPacket.from(input);
        assertFalse(pairPacket.isInOrder());
    }
}