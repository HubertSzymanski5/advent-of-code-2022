package pl.szymhu.day11;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import pl.szymhu.utils.InputReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonkeyGangTest {

    @Test
    @SneakyThrows
    void shouldPassAdventOfCodeExamplePartI() {
        var input = InputReader.readEmptyLineSeparatedStringList("example-input.txt", this.getClass());

        MonkeyGang monkeyGang = MonkeyGang.initialize(input);
        assertEquals(4, monkeyGang.getMonkeys().size());

        assertEquals(10605, monkeyGang.findLevelOfMonkeyBusiness());
    }

}