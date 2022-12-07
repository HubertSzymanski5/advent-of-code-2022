package pl.szymhu.day07;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FileSystem {

    private static final String LS_COMMAND = "ls";
    private static final String CD_COMMAND = "cd";

    private final FileTree root;

    public String tree() {
        return root.toString();
    }

    public int getDirectoriesWithSizeLessThan(int n) {
        return root.getDirectories().stream()
                .map(Directory::getSize)
                .filter(size -> size <= n)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public static FileSystem initialize(List<String> cmd) {
        FileTree fileTree = new Directory("/");
        FileTree currentDir = fileTree;
        for (int i = 1; i < cmd.size(); i++) {
            String[] cmdLine = cmd.get(i).split(" ");
            if (!"$".equals(cmdLine[0])) {
                continue;
            }
            switch (cmdLine[1]) {
                case LS_COMMAND -> lsCommand(cmd, i, currentDir);
                case CD_COMMAND -> currentDir = currentDir.changeDirectory(cmdLine[2]);
                default -> System.out.println("shouldn't get there?!");
            }
        }
        return new FileSystem(fileTree);
    }

    private static void lsCommand(List<String> cmd, int i, FileTree currentDir) {
        for (int j = i + 1; j < cmd.size() && !cmd.get(j).startsWith("$"); j++) {
            String[] cmdLine = cmd.get(j).split(" ");
            if ("dir".equals(cmdLine[0])) {
                Directory dir = new Directory(cmdLine[1]);
                currentDir.addFileTree(dir);
            } else {
                File file = new File(cmdLine[1], Integer.parseInt(cmdLine[0]));
                currentDir.addFileTree(file);
            }
        }
    }
}
