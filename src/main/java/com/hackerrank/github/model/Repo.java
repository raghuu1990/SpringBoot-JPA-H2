package com.hackerrank.github.model;

public class Repo {
    private Long id;
    private String name;
    private String url;

    public Repo() {
    }

    public Repo(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" {\n     \"");
        if (id != null) {
            builder.append("id\" : \"");
            builder.append(id);
            builder.append("\",\n     \"");
        }
        if (name != null) {
            builder.append("name\" : \"");
            builder.append(name);
            builder.append("\",\n     \"");
        }
        if (url != null) {
            builder.append("url\" : \"");
            builder.append(url);
        }
        builder.append("\"\n  }");
        return builder.toString();
    }
}