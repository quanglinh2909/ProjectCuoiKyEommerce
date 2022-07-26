package com.example.projectcuoikyeommerce.model;

public class Term {
    private String title;
    private String content;

    public Term(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}