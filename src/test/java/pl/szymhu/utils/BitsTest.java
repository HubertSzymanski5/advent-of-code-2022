package pl.szymhu.utils;

import org.junit.jupiter.api.Test;
import pl.szmhu.utils.Bits;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BitsTest {

    @Test
    void shouldCreateBitsFromString() {
        var bits = Bits.valueOf("1011");
        assertAll(
                () -> assertTrue(bits.get(0)),
                () -> assertTrue(bits.get(1)),
                () -> assertFalse(bits.get(2)),
                () -> assertTrue(bits.get(3)));
    }

    @Test
    void shouldReturnNthBit() {
        var bits = Bits.valueOf("00001000");
        assertTrue(bits.get(3));
    }

    @Test
    void shouldReturnInteger() {
        var bits = Bits.valueOf("10001010100101");
        assertEquals(8869, bits.toInt());
    }

    @Test
    void shouldReturnBitsLength() {
        var bits = Bits.valueOf("1001001010101010");
        assertEquals(16, bits.size());
    }

    @Test
    void shouldFormatBitsToString() {
        String input = "1010101110101010010100101010101010101010001001000001111001001001001001";
        assertEquals(input, Bits.valueOf(input).toString());
    }

    @Test
    void shouldCreateBitsFromBooleanList() {
        List<Boolean> list = List.of(true, true, true, false, true, false, true);
        assertEquals(Bits.valueOf("1010111"), Bits.from(list));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionOnInvalidString() {
        String input = "110002101001";
        assertThrows(IllegalArgumentException.class, () -> Bits.valueOf(input));
    }

    @Test
    void shouldReturnInvertedBits() {
        var bits = Bits.valueOf("1010101");
        var inverted = Bits.valueOf("0101010");

        assertEquals(inverted, bits.inverted());
    }

    @Test
    void shouldPerformXOROperation() {
        var bits1 = Bits.valueOf("1010");
        var bits2 = Bits.valueOf("1100");

        assertEquals(Bits.valueOf("0110"), bits1.xor(bits2));
    }
}