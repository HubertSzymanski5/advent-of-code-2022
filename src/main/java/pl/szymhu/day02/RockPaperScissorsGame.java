package pl.szymhu.day02;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RockPaperScissorsGame {

    private List<GamePicks> gamePicksList;
    private RockPaperScissorsStrategy strategy;

    public int calculateTotalScore() {
        return gamePicksList.stream()
                .map(strategy::getScoresFrom)
                .reduce(Integer::sum)
                .orElse(0);
    }
}

