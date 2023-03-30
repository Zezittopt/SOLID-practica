package com.kreitek.files;

import java.util.List;

public interface FileSystemItem {
    String getName();
    FileSystemItem getParent();
    String getFullPath();
    void open();
    void close();
    void setPosition(int numberOfBytesFromBeginning);
    byte[] read(int numberOfBytesToRead);
    void write(byte[] buffer);
}

public interface DirectoryItem extends FileSystemItem {
    List<FileSystemItem> listFiles();
    void addFile(FileSystemItem file);
    void removeFile(FileSystemItem file);
}

public interface FileItem extends FileSystemItem {
    String getExtension();
    int getSize();
}
