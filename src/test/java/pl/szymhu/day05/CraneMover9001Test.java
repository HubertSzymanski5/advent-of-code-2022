package pl.szymhu.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CraneMover9001Test {

    @Test
    void shouldPassAdventOfCodeExamplePartII() {
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

        var crane = CraneMover9001.initialize(commands, stacks);
        crane.executeCommands();

        System.out.println(crane.getSupplyStacks().getStacks());

        assertEquals("MCD", crane.getSupplyStacks().getCratesFromTheTopString());
    }

}