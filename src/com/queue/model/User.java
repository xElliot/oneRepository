package com.queue.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by JUSTIN on 2015/7/29.
 */
@Entity
@Table(name = "_user", schema = "", catalog = "food")
public class User {
    private int id;
    private String usernumber;
    private String username;
    private String password;
    private String words;
    private String img;
    private String nickname;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_favor",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
    public Set<Restaurant> getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Set<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }

    private Set<Restaurant> restaurant;


    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "usernumber")
    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "words")
    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    @Basic
    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (img != null ? !img.equals(user.img) : user.img != null) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (usernumber != null ? !usernumber.equals(user.usernumber) : user.usernumber != null) return false;
        if (words != null ? !words.equals(user.words) : user.words != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (usernumber != null ? usernumber.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (words != null ? words.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        return result;
    }
}
