package com.codecool.web.model;

public class Poem extends AbstractModel {

    private final String title;
    private final String content;

    public Poem(int id, String title, String content) {
        super(id);
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
