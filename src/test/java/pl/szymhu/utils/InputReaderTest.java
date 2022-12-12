package pl.szymhu.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static pl.szymhu.utils.InputReader.*;

class InputReaderTest {


    @Test
    void shouldReadIntegerCommaSeparatedList() throws IOException {
        var inputFileName = "integers-comma-list.txt";

        var result = readIntCommaList(inputFileName, this.getClass());

        assertEquals(List.of(5, 4, 3, 2, 1), result);
    }

    @Test
    void shouldReadIntegerList() throws IOException {
        var inputFileName = "integers-list.txt";

        var result = readIntList(inputFileName, this.getClass());

        assertEquals(List.of(1, 2, 3, 4), result);
    }

    @Test
    void shouldReadStringList() throws IOException {
        var inputFileName = "strings-list.txt";

        var result = readStringList(inputFileName, this.getClass());

        assertEquals(List.of("string1", "string2", "string3", "string4"), result);
    }

    @Test
    void shouldReadEmptyLineSeparatedStringList() throws IOException {
        var inputFileName = "strings-empty-line-list.txt";

        var result = readEmptyLineSeparatedStringList(inputFileName, this.getClass());
        var expected = List.of(
                "One string part\nWith empty line next",
                "Second string part\nWith empty line next",
                "Last one");

        assertEquals(expected, result);
    }
}
