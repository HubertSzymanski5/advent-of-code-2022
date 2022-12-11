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

        Monkey monkey = Monkey.from(stringInput);

        assertAll(
                () -> assertIterableEquals(List.of(79, 98), monkey.getItems()),
                () -> assertEquals(19, monkey.getOperation().apply(1)),
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

        Monkey monkey = Monkey.from(stringInput);

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

        Monkey monkey = Monkey.from(stringInput);

        monkey.catchItem(230);
        monkey.catchItems(List.of(23, 12));

        assertIterableEquals(List.of(79, 98, 230, 23, 12), monkey.getItems());
    }
}