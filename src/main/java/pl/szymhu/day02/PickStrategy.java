package pl.szymhu.day02;

import java.util.Map;

import static pl.szymhu.day02.Pick.*;

public class PickStrategy implements RockPaperScissorsStrategy {
    private static final Map<Character, Pick> PICK_MAP = Map.of(
            'A', ROCK,
            'B', PAPER,
            'C', SCISSORS,
            'X', ROCK,
            'Y', PAPER,
            'Z', SCISSORS);

    @Override
    public int getScoresFrom(GamePicks gamePicks) {
        Pick opponentPick = PICK_MAP.get(gamePicks.opponentPick());
        Pick playerPick = PICK_MAP.get(gamePicks.playerStrategy());

        return playerPick.getPoints() + playerPick.against(opponentPick).getPoints();
    }
}
