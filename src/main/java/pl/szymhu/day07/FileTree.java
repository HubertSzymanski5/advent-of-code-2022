package pl.szymhu.day07;

import java.util.List;

public interface FileTree {

    int getSize();

    String getName();

    void setParent(FileTree fileTree);

    void addFileTree(FileTree fileTree);

    FileTree changeDirectory(String name);

    List<Directory> getDirectories();
}
