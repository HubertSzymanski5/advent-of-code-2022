package pl.szymhu.day08;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

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
