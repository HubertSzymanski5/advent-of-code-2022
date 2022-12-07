package pl.szymhu.day07;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class File implements FileTree {

    private String name;
    private int size;

    @Override
    public void setParent(FileTree fileTree) {
        // do nothing
    }

    @Override
    public void addFileTree(FileTree fileTree) {
        throw new RuntimeException(name + " is not a directory!");
    }

    @Override
    public FileTree changeDirectory(String name) {
        throw new RuntimeException(name + " is not a directory!");
    }

    @Override
    public List<Directory> getDirectories() {
        return List.of();
    }

    @Override
    public String toString() {
        return name + " (file, size=" + size + ")";
    }
}
