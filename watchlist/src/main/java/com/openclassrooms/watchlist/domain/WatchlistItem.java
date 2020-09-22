package com.openclassrooms.watchlist.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class WatchlistItem {
    public String priority;
    //@NotBlank(message = "this can't be blank")
    private String title;
    private String rating;
   //private String priority;
   // @Size(max=50, message="at most 50 characters")
    private String comment;
    private Integer id;

    public WatchlistItem(String title, String rating, String priority, String comment, Integer id) {
        this.title = title;
        this.rating = rating;
        this.priority = priority;
        this.comment = comment;
        this.id = id;
    }

    public WatchlistItem() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
