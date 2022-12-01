package pl.szymhu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class InputReader {

    public static final String NEW_LINE = "\\r?\\n";
    public static final String EMPTY_LINE = NEW_LINE + NEW_LINE;
    public static final String COMMA = ",|" + NEW_LINE;

    public static List<Integer> readIntCommaList(String filePath, Class<?> cls) throws IOException {
        var rawString = fileToString(filePath, cls);
        return Arrays.stream(rawString.split(COMMA)).map(Integer::parseInt).toList();
    }

    public static List<Integer> readIntList(String filePath, Class<?> cls) throws IOException {
        var rawString = fileToString(filePath, cls);
        return Arrays.stream(rawString.split(NEW_LINE)).map(Integer::parseInt).toList();
    }

    public static List<String> readStringList(String filePath, Class<?> cls) throws IOException {
        var rawString = fileToString(filePath, cls);
        return Arrays.stream(rawString.split(NEW_LINE)).toList();
    }

    public static List<String> readEmptyLineSeparatedStringList(String filePath, Class<?> cls) throws IOException {
        var rawString = fileToString(filePath, cls);
        return Arrays.stream(rawString.split(EMPTY_LINE))
                .map(String::trim)
                .toList();
    }

    public static String fileToString(String filePath, Class<?> cls) throws IOException {
        try (InputStream inputStream = cls.getResourceAsStream(filePath)) {
            assert inputStream != null;
            return new String(inputStream.readAllBytes(), UTF_8);
        }
    }
}
