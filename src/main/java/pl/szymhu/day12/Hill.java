package pl.szymhu.day12;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Stream;

import static pl.szymhu.utils.InputReader.NEW_LINE;

@AllArgsConstructor
@Getter
public class Hill {

    private List<List<GridItem>> grid;

    public int getMinStepsToGetToEnd() {
        GridItem start = grid.stream()
                .flatMap(Collection::stream)
                .filter(item -> item.getHeight() == 'S')
                .findFirst()
                .orElseThrow();
        return go(start);
    }

    public int getMinStepsFromAnyASquare() {
        List<GridItem> startingPoints = grid.stream()
                .flatMap(Collection::stream)
                .filter(i -> i.getHeight() == 'a')
                .toList();
        var otherAMin= startingPoints.stream()
                .map(this::go)
                .reduce(Integer::min)
                .orElse(Integer.MAX_VALUE);
        return Math.min(getMinStepsToGetToEnd(), otherAMin);
    }

    int go(GridItem start) {
        grid.stream().flatMap(Collection::stream)
                        .forEach(item -> item.setMinStepsToReach(null));
        start.setMinStepsToReach(0);
        Deque<GridItem> toVisit = new ArrayDeque<>(List.of(start));
        boolean hasFound = false;
        while (!hasFound && !toVisit.isEmpty()) {
            GridItem currentlyProcessed = toVisit.pop();
            var newItems = currentlyProcessed.visit();
            hasFound = newItems.stream()
                    .map(GridItem::getHeight)
                    .anyMatch(h -> h == 'E');
            toVisit.addAll(newItems);
        }
        return toVisit.stream()
                .filter(i -> i.getHeight() == 'E')
                .map(GridItem::getMinStepsToReach)
                .findFirst()
                .orElse(Integer.MAX_VALUE);
    }

    public static Hill initialize(String stringGrid) {
        var grid = Arrays.stream(stringGrid.split(NEW_LINE))
                .map(Hill::toGridItemList)
                .toList();
        int maxY = grid.size();
        int maxX = grid.get(0).size();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                GridItem pos = grid.get(y).get(x);
                Stream.of(
                                new int[]{x - 1, y},
                                new int[]{x + 1, y},
                                new int[]{x, y - 1},
                                new int[]{x, y + 1})
                        .filter(p -> 0 <= p[0] && p[0] < maxX)
                        .filter(p -> 0 <= p[1] && p[1] < maxY)
                        .map(p -> grid.get(p[1]).get(p[0]))
                        .filter(neighbour -> isAtMostOneLevelAbove(pos, neighbour))
                        .forEach(pos::addNeighbour);
            }
        }
        return new Hill(grid);
    }

    private static boolean isAtMostOneLevelAbove(GridItem pos, GridItem neighbour) {
        var valuesToMap = Map.of(
                'S', 'a',
                'E', 'z');
        char currentPos = valuesToMap.containsKey(pos.getHeight()) ? valuesToMap.get(pos.getHeight()) : pos.getHeight();
        char neighbourPos = valuesToMap.containsKey(neighbour.getHeight()) ? valuesToMap.get(neighbour.getHeight()) : neighbour.getHeight();
        return currentPos + 1 >= neighbourPos;
    }

    private static List<GridItem> toGridItemList(String heightsRow) {
        return heightsRow.chars()
                .mapToObj(GridItem::with)
                .toList();
    }
}

@AllArgsConstructor
@Getter
class GridItem {
    private final char height;
    @Setter
    private Integer minStepsToReach;
    private List<GridItem> neighbours;

    public List<GridItem> visit() {
        return neighbours.stream()
                .filter(neighbours -> neighbours.minStepsToReach == null)
                .peek(neighbour -> neighbour.setMinStepsToReach(minStepsToReach + 1))
                .toList();
    }

    public void addNeighbour(GridItem gridItem) {
        neighbours.add(gridItem);
    }

    @Override
    public String toString() {
        return String.valueOf(height) + ": " + minStepsToReach;
    }

    public static GridItem with(int height) {
        return new GridItem((char) height, null, new ArrayList<>());
    }
}
