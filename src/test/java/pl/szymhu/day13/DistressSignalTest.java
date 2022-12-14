package pl.szymhu.day13;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import pl.szymhu.utils.InputReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class DistressSignalTest {

    @Test
    @SneakyThrows
    void shouldPassAdventOfCodeExamplePartI() {
        var input = InputReader.readEmptyLineSeparatedStringList("example-input.txt", this.getClass());

        DistressSignal signal = DistressSignal.initialize(input);

        assertIterableEquals(List.of(1, 2, 4, 6), signal.getIndexesOfPairsInRightOrder());
        assertEquals(13, signal.getSumOfRightOrderedIndexes());
        assertEquals(140, signal.getDecoderKey());
    }

}