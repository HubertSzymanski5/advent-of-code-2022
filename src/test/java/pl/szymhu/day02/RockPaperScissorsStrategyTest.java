package pl.szymhu.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsStrategyTest {

    @Test
    void shouldPassAdventOfCodeExamplePartI() {
        var input = List.of(
                new GamePicks('A', 'Y'),
                new GamePicks('B', 'X'),
                new GamePicks('C', 'Z'));

        var game = new RockPaperScissorsGame(input, new PickStrategy());

        assertEquals(15, game.calculateTotalScore());
    }

    @Test
    void shouldPassAdventOfCodeExamplePartII() {
        var input = List.of(
                new GamePicks('A', 'Y'),
                new GamePicks('B', 'X'),
                new GamePicks('C', 'Z'));

        var game = new RockPaperScissorsGame(input, new OutcomeStrategy());

        assertEquals(12, game.calculateTotalScore());
    }
}