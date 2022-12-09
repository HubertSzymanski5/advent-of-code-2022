package pl.szymhu.day08;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.szymhu.utils.InputReader.NEW_LINE;

@AllArgsConstructor
public class Forrest {

    private Tree[][] trees;

    public long countVisibleTrees() {
        return Arrays.stream(trees)
                .flatMap(Arrays::stream)
                .filter(Tree::isVisible)
                .count();
    }

    public long findBestTreeScenicScore() {
        List<Long> scenicScores = new ArrayList<>();
        for (int i = 0; i < trees.length; i++) {
            for (int j = 0; j < trees.length; j++) {
                scenicScores.add(scenicScoreAt(i, j));
            }
        }
        return scenicScores.stream()
                .reduce(Long::max)
                .orElse(0L);
    }

    private long scenicScoreAt(int x, int y) {
        long right = 0, left = 0, up = 0, down = 0;
        // right
        for (int i = x + 1; i < trees.length; i++) {
            right++;
            if (trees[i][y].height >= trees[x][y].height) {
                break;
            }
        }
        // left
        for (int i = x - 1; i >= 0; i--) {
            left++;
            if (trees[i][y].height >= trees[x][y].height) {
                break;
            }
        }
        // up
        for (int i = y - 1; i >= 0; i--) {
            up++;
            if (trees[x][i].height >= trees[x][y].height) {
                break;
            }
        }
        // down
        for (int i = y + 1; i < trees.length; i++) {
            down++;
            if (trees[x][i].height >= trees[x][y].height) {
                break;
            }
        }
        return right * left * up * down;
    }

    private void markVisible() {
        // top down
        for (Tree[] col : trees) {
            int maxHeight = -1;
            for (int y = 0; y < trees.length; y++) {
                if (col[y].height > maxHeight) {
                    maxHeight = col[y].height;
                    col[y].isVisible = true;
                }
            }
            maxHeight = -1;
            for (int y = trees.length - 1; y >= 0; y--) {
                if (col[y].height > maxHeight) {
                    maxHeight = col[y].height;
                    col[y].isVisible = true;
                }
            }
        }
        // left right
        for (int y = 0; y < trees.length; y++) {
            int maxHeight = -1;
            for (Tree[] tree : trees) {
                if (tree[y].height > maxHeight) {
                    maxHeight = tree[y].height;
                    tree[y].isVisible = true;
                }
            }
            maxHeight = -1;
            for (int x = trees.length - 1; x >= 0; x--) {
                if (trees[x][y].height > maxHeight) {
                    maxHeight = trees[x][y].height;
                    trees[x][y].isVisible = true;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trees.length; i++) {
            for (int j = 0; j < trees[i].length; j++) {
                if (trees[j][i].isVisible) {
                    sb.append("[").append(trees[j][i].height).append("]");
                } else {
                    sb.append(" ").append(trees[j][i].height).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static Forrest initialize(String forrest) {
        var trees2dList = Arrays.stream(forrest.split(NEW_LINE))
                .map(row -> row.chars().mapToObj(Character::getNumericValue).toList())
                .toList();
        if (trees2dList.get(0).size() != trees2dList.size()) {
            throw new RuntimeException("Forrest must be a square!");
        }
        Tree[][] trees = new Tree[trees2dList.get(0).size()][trees2dList.size()];
        for (int y = 0; y < trees2dList.size(); y++) {
            for (int x = 0; x < trees2dList.get(y).size(); x++) {
                trees[x][y] = new Tree(trees2dList.get(y).get(x), false);
            }
        }
        Forrest result = new Forrest(trees);
        result.markVisible();
        return result;
    }
}

@AllArgsConstructor
class Tree {

    int height;

    @Getter
    boolean isVisible;
}
