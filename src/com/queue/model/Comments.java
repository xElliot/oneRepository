package com.queue.model;

import java.sql.Timestamp;

/**
 * Created by JUSTIN on 2015/7/23.
 */
public class Comments {
    private Integer id;
    private String comments;
    private Timestamp date;
    private Restaurant restaurant;
    private User user;
    private Integer stars;

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public  User getUser() {
        return user;
    }

    public void setUser( User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comments comments1 = (Comments) o;

        if (id != comments1.id) return false;
        if (comments != null ? !comments.equals(comments1.comments) : comments1.comments != null) return false;
        if (date != null ? !date.equals(comments1.date) : comments1.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
