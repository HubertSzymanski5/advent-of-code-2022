package pl.szymhu.day10;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import pl.szymhu.utils.InputReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrtTest {

    @Test
    @SneakyThrows
    void shouldPassAdventOfCodeExamplePartII() {
        var input = InputReader.readStringList("example-input.txt", this.getClass());
        Crt crt = Crt.init(input);
        var expected = """
                
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....""";
        assertEquals(expected, crt.generate());
    }
}