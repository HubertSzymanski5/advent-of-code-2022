package pl.szymhu.day05;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class CraneMover9000Test {

    @Test
    void shouldInitializeWithCommands() {
        var commands = List.of(
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2");
        var stacks = """
                [A]
                 1
                """;

        var crane = CraneMover9000.initialize(commands, stacks);

        var expected = List.of(
                new CraneCommand(1, 2, 1),
                new CraneCommand(3, 1, 3),
                new CraneCommand(2, 2, 1),
                new CraneCommand(1, 1, 2));
        assertEquals(expected, crane.getCommands());
    }

    @Test
    void shouldExecuteMoveCommand() {
        var commands = List.of(
                "move 1 from 2 to 1");
        var stacks = """
                [A] [B]
                [C] [D]
                 1   2
                """;

        var crane = CraneMover9000.initialize(commands, stacks);

        crane.executeCommands();
        assertIterableEquals(new ArrayDeque<>(List.of('C', 'A', 'B')),
                crane.getSupplyStacks().getStacks().get(0));
        assertIterableEquals(new ArrayDeque<>(List.of('D')),
                crane.getSupplyStacks().getStacks().get(1));
    }

    @Test
    void shouldPassAdventOfCodeExamplePartI() {
        var commands = List.of(
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2");
        var stacks = """
                    [D]
                [N] [C]
                [Z] [M] [P]
                 1   2   3
                """;

        var crane = CraneMover9000.initialize(commands, stacks);
        crane.executeCommands();

        System.out.println(crane.getSupplyStacks().getStacks());

        assertEquals("CMZ", crane.getSupplyStacks().getCratesFromTheTopString());
    }
}