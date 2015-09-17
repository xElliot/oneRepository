package com.queue.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by JUSTIN on 2015/7/27.
 */
@Entity
@Table(name = "_order", schema = "", catalog = "food")
public class Order {
    private int id;
    private String ordernumber;
    private Timestamp date;
    private int state;
    private int desk;
//    private int restaurantId;
//    private int userId;
    private Restaurant restaurant;
    private User user;
    private String note;

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ordernumber")
    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "desk")
    public int getDesk() {
        return desk;
    }

    public void setDesk(int desk) {
        this.desk = desk;
    }

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (desk != order.desk) return false;
        if (id != order.id) return false;
        if (state != order.state) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (ordernumber != null ? !ordernumber.equals(order.ordernumber) : order.ordernumber != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ordernumber != null ? ordernumber.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + desk;
        return result;
    }
}
