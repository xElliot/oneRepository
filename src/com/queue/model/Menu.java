package com.queue.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by JUSTIN on 2015/7/29.
 */
@Entity
@Table(name = "_menu", schema = "", catalog = "food")
public class Menu {
    private int id;
    private int restaurant;
    private String dish;
    private int price;
    private String img;
    private Set<DishKind> dishkind;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "menu_dishkind",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dishkind_id"))
    public Set<DishKind> getDishkind() {
        return dishkind;
    }

    public void setDishkind(Set<DishKind> dishkind) {
        this.dishkind = dishkind;
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
    @Column(name = "restaurant")
    public int getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(int restaurant) {
        this.restaurant = restaurant;
    }

    @Basic
    @Column(name = "dish")
    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (id != menu.id) return false;
        if (price != menu.price) return false;
        if (restaurant != menu.restaurant) return false;
        if (dish != null ? !dish.equals(menu.dish) : menu.dish != null) return false;
        if (img != null ? !img.equals(menu.img) : menu.img != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + restaurant;
        result = 31 * result + (dish != null ? dish.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (img != null ? img.hashCode() : 0);
        return result;
    }
}
