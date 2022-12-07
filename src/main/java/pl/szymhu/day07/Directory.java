package pl.szymhu.day07;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Directory implements FileTree {
    private static final String PARENT = "..";

    private final List<FileTree> subFiles = new ArrayList<>();

    @Getter
    private final String name;

    @Setter
    private FileTree parent;

    public Directory(String name) {
        this.name = name;
    }

    public int getSize() {
        return subFiles.stream()
                .map(FileTree::getSize)
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public FileTree changeDirectory(String name) {
        if (PARENT.equals(name)) {
            return parent;
        }
        return subFiles.stream()
                .filter(fileTree -> name.equals(fileTree.getName()))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<Directory> getDirectories() {
        List<Directory> result = new ArrayList<>();
        result.add(this);
        List<Directory> subDirs = subFiles.stream()
                .map(FileTree::getDirectories)
                .flatMap(Collection::stream)
                .toList();
        result.addAll(subDirs);
        return result;
    }

    @Override
    public void addFileTree(FileTree fileTree) {
        fileTree.setParent(this);
        subFiles.add(fileTree);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" (dir)").append("\n");
        subFiles.forEach(subFile -> Arrays.stream(subFile.toString().split("\n"))
                .toList()
                .forEach(line -> sb.append("\t").append(line).append("\n")));
        return sb.toString();
    }
}
