package pl.szymhu.day07;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileSystemTest {

    @Test
    void shouldInitializeWithSimpleCommands() {
        var input = Arrays.stream("""
                $ cd /
                $ ls
                dir a
                4321 b.txt
                $ cd a
                $ ls
                1234 c.txt
                """.split("\n")).toList();

        FileSystem fileSystem = FileSystem.initialize(input);

        String expectedTree = """
                / (dir)
                \ta (dir)
                \t\tc.txt (file, size=1234)
                \tb.txt (file, size=4321)
                """;
        assertEquals(expectedTree, fileSystem.tree());
    }

    @Test
    void shouldPassAdventOfCodeExample() {
        var input = Arrays.stream("""
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir e
                29116 f
                2557 g
                62596 h.lst
                $ cd e
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k
                """.split("\n")).toList();

        FileSystem fileSystem = FileSystem.initialize(input);

        String expectedTree = """
                / (dir)
                \ta (dir)
                \t\te (dir)
                \t\t\ti (file, size=584)
                \t\tf (file, size=29116)
                \t\tg (file, size=2557)
                \t\th.lst (file, size=62596)
                \tb.txt (file, size=14848514)
                \tc.dat (file, size=8504156)
                \td (dir)
                \t\tj (file, size=4060174)
                \t\td.log (file, size=8033020)
                \t\td.ext (file, size=5626152)
                \t\tk (file, size=7214296)
                """;
        assertEquals(expectedTree, fileSystem.tree());
        assertEquals(95437, fileSystem.getDirectoriesSumSizeWithSizeLessThan(100_000));
        assertEquals(24933642, fileSystem.getDirectorySizeToDelete());
    }
}