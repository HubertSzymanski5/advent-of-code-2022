package pl.szymhu.day11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonkeyTest {

    @Test
    void shouldInitialize() {
        var stringInput = """
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                """;

        Monkey monkey = Monkey.from(stringInput, true);

        assertAll(
                () -> assertIterableEquals(List.of(79L, 98L), monkey.getItems()),
                () -> assertEquals(19, monkey.getOperation().apply(1L)),
                () -> assertEquals(23, monkey.getTestNumber()),
                () -> assertEquals(2, monkey.getThrowToMapping().get(true)),
                () -> assertEquals(3, monkey.getThrowToMapping().get(false)));
    }

    @Test
    void shouldInspectAndThrowItems() {
        var stringInput = """
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                """;

        Monkey monkey = Monkey.from(stringInput, true);

        var expected = List.of(
                new MonkeyThrowResult(3, 500),
                new MonkeyThrowResult(3, 620));
        assertIterableEquals(expected, monkey.processItems());
    }

    @Test
    void shouldReceiveItems() {
        var stringInput = """
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                """;

        Monkey monkey = Monkey.from(stringInput, true);

        monkey.catchItem(230);

        assertIterableEquals(List.of(79L, 98L, 230L), monkey.getItems());
    }

    @Test
    void shouldInspectAndThrowItemsWhenNotBored() {
        var stringInput = """
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                """;

        Monkey monkey = Monkey.from(stringInput, false);

        var expected = List.of(
                new MonkeyThrowResult(3, 1501),
                new MonkeyThrowResult(3, 1862));
        assertIterableEquals(expected, monkey.processItems());
    }
}