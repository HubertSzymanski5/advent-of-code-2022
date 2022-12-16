package pl.szymhu.day14;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class SandVessel {

    private char[][] view;

    public int pourSand() {
        int count = 0;
        while (addSand()) {
            count++;
        }
        return count;
    }

    boolean addSand() {
        int[] pos = {500, 0};
        while (pos[0] < view.length - 1 && pos[1] < view[0].length - 1) {
            if (view[pos[0]][pos[1] + 1] == '.') {
                pos[1]++;
                continue;
            } else if (view[pos[0] - 1][pos[1] + 1] == '.') {
                pos[1]++;
                pos[0]--;
                continue;
            } else if (view[pos[0] + 1][pos[1] + 1] == '.') {
                pos[1]++;
                pos[0]++;
                continue;
            }
            view[pos[0]][pos[1]] = 'o';
            return true;
        }
        return false;
    }

    void addRock(List<List<Integer>> rock) {
        for (int i = 1; i < rock.size(); i++) {
            var start = rock.get(i - 1);
            var end = rock.get(i);
            int dx = end.get(0) - start.get(0);
            int dy = end.get(1) - start.get(1);
            if (dx == 0) {
                for (int y = start.get(1); y != end.get(1) + dy / Math.abs(dy); y += dy / Math.abs(dy)) {
                    view[start.get(0)][y] = '#';
                }
            } else {
                for (int x = start.get(0); x != end.get(0) + dx / Math.abs(dx); x += dx / Math.abs(dx)) {
                    view[x][start.get(1)] = '#';
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < view[0].length; j++) {
            for (int i = 0; i < view.length; i++) {
                sb.append(view[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static SandVessel initialize(List<String> stringRocks) {
        List<List<List<Integer>>> rocks = stringRocks.stream().map(SandVessel::toListOfPoints).toList();
        int[] maxSize = {0, 0};
        rocks.stream().flatMap(Collection::stream).forEach(point -> {
            maxSize[0] = Math.max(maxSize[0], point.get(0));
            maxSize[1] = Math.max(maxSize[1], point.get(1));
        });
        char[][] view = new char[maxSize[0] + 2][maxSize[1] + 2];
        for (char[] chars : view) {
            Arrays.fill(chars, '.');
        }
        SandVessel sandVessel = new SandVessel(view);
        rocks.forEach(sandVessel::addRock);
        return sandVessel;
    }

    private static List<List<Integer>> toListOfPoints(String string) {
        return Arrays.stream(string.split(" -> ")).map(SandVessel::stringToPoint).toList();
    }

    private static List<Integer> stringToPoint(String stringPoint) {
        return Arrays.stream(stringPoint.split(",")).map(Integer::parseInt).toList();
    }

    private static void addRockToView() {

    }
}
