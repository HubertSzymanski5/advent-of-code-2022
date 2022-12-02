package pl.szymhu.day02;

import java.util.Arrays;
import java.util.Map;

import static pl.szymhu.day02.Outcome.*;
import static pl.szymhu.day02.Pick.*;
import static pl.szymhu.day02.Pick.SCISSORS;

public class OutcomeStrategy implements RockPaperScissorsStrategy {

    private static final Map<Character, Pick> PICK_MAP = Map.of(
            'A', ROCK,
            'B', PAPER,
            'C', SCISSORS);

    private static final Map<Character, Outcome> OUTCOME_MAP = Map.of(
            'X', LOSE,
            'Y', DRAW,
            'Z', WIN);

    @Override
    public int getScoresFrom(GamePicks gamePicks) {
        Pick opponentPick = PICK_MAP.get(gamePicks.opponentPick());
        Outcome outcome = OUTCOME_MAP.get(gamePicks.playerStrategy());
        Pick playerPick = Arrays.stream(Pick.values())
                .filter(p -> outcome == p.against(opponentPick))
                .findFirst().orElseThrow();

        return playerPick.getPoints() + outcome.getPoints();
    }
}
