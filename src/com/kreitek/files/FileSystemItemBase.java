package com.kreitek.files;

import java.util.List;

public abstract class FileSystemItemBase implements FileSystemItem {
    protected static final String PATH_SEPARATOR = "/";
    protected String name;
    protected FileSystemItem parent;

    protected FileSystemItemBase(FileSystemItem parent, String name) {
        setName(name);
        setParent(parent);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }
       this.name = name;
    }

    @Override
    public FileSystemItem getParent() {
        return parent;
    }

    @Override
    public void setParent(FileSystemItem directory) {
        if (directory != null && !(directory instanceof Directory)) {
            throw new IllegalArgumentException("El padre solo puede ser un directorio");
        }
        if (this.parent != directory) {
            if (this.parent != null) this.parent.removeFile(this);
            this.parent = directory;
            if (directory != null) directory.addFile(this);
        }
    }

    @Override
    public String getFullPath() {
        String path = PATH_SEPARATOR;
        if (parent != null) {
            String parentFullPath = parent.getFullPath();
            path = parent.getFullPath() + (parentFullPath.length() > 1 ? PATH_SEPARATOR : "");
        }
        path = path + getName();
        return path;
    }
@Override
    public String getExtension() {
        int dotIndex = name.lastIndexOf(".");
        if (dotIndex > -1 && dotIndex < name.length() - 1) {
            return name.substring(dotIndex + 1);
        }
        return "";
    }

    @Override
    public List<FileSystemItem> listFiles() {
        return new ArrayList<>();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void open() {
        throw new UnsupportedOperationException("No se puede abrir este tipo de archivo");
    }

    @Override
    public void setPosition(int numberOfBytesFromBeginning) {
        throw new UnsupportedOperationException("Este tipo de archivo no admite establecer la posición");
    }

    @Override
    public byte[] read(int numberOfBytesToRead) {
        throw new UnsupportedOperationException("No se puede leer este tipo de archivo");
    }

    @Override
    public void write(byte[] buffer) {
        throw new UnsupportedOperationException("No se puede escribir en este tipo de archivo");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("No se puede cerrar este tipo de archivo");
    }
}