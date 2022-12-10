package pl.szymhu.day10;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import pl.szymhu.utils.InputReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CpuTest {

    @Test
    void shouldPassSimpleExample() {
        var inputs = List.of("noop",
                "addx 3",
                "addx -5");

        Cpu cpu = Cpu.init(inputs);

        cpu.cycleOnce();
        assertEquals(1, cpu.getRegX());
        assertEquals(1, cpu.getClockCount());

        cpu.cycleOnce();
        assertEquals(1, cpu.getRegX());
        assertEquals(2, cpu.getClockCount());

        cpu.cycleOnce();
        assertEquals(4, cpu.getRegX());
        assertEquals(3, cpu.getClockCount());

        cpu.cycleOnce();
        assertEquals(4, cpu.getRegX());
        assertEquals(4, cpu.getClockCount());

        cpu.cycleOnce();
        assertEquals(-1, cpu.getRegX());
        assertEquals(5, cpu.getClockCount());
    }

    @Test
    @SneakyThrows
    void shouldPassAdventOfCodeExamplePartI() {
        List<String> inputs = InputReader.readStringList("example-input.txt", this.getClass());

        Cpu cpu = Cpu.init(inputs);

        assertEquals(13140, cpu.getSumOfSignalStrengths());
    }

}