package com.queue.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by JUSTIN on 2015/7/29.
 */
@Entity
@Table(name = "_dishKind", schema = "", catalog = "food")
public class DishKind {
    private int id;
    private String name;
    private Set<Menu> menu;

    @ManyToMany(mappedBy = "dishkind",cascade = CascadeType.ALL)
    public Set<Menu> getMenu() {
        return menu;
    }

    public void setMenu(Set<Menu> menu) {
        this.menu = menu;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishKind dishkind = (DishKind) o;

        if (id != dishkind.id) return false;
        if (name != null ? !name.equals(dishkind.name) : dishkind.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
