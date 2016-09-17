package com.mohamed265.movieapp.entity;

import java.io.Serializable;

public class Review  implements Serializable{

    private String id;
    private String author;
    private String content;

    public Review() {
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return "https://www.themoviedb.org/review/" + id;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return The content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    public void setContent(String content) {
        this.content = content;
    }


}