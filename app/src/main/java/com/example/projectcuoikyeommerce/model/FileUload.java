package com.example.projectcuoikyeommerce.model;

public class FileUload {
    private String originalname;
    private String filename;
    private String path;

    public FileUload(String originalname, String filename, String path) {
        this.originalname = originalname;
        this.filename = filename;
        this.path = path;
    }

    public String getOriginalname() {
        return originalname;
    }

    public void setOriginalname(String originalname) {
        this.originalname = originalname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "FileUload{" +
                "originalname='" + originalname + '\'' +
                ", filename='" + filename + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
