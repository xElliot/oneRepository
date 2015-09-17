package com.queue.model;

import javax.persistence.*;

/**
 * Created by JUSTIN on 2015/8/4.
 */
@Entity
@Table(name = "_restaurant", schema = "", catalog = "food")
public class Restaurant {
    private int id;
    private String number;
    private String name;
    private String tel;
    private String address;
    private int consumption;
    private int stars;
    private String img;
    private int expected;
    private String recommendImg;
    private String recommendWord;
    private int tinyTable;
    private int middleTable;
    private int bigTable;
    private String longitude;
    private String latitude;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TEL")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "consumption")
    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    @Basic
    @Column(name = "stars")
    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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
    @Column(name = "expected")
    public int getExpected() {
        return expected;
    }

    public void setExpected(int expected) {
        this.expected = expected;
    }

    @Basic
    @Column(name = "recommendImg")
    public String getRecommendImg() {
        return recommendImg;
    }

    public void setRecommendImg(String recommendImg) {
        this.recommendImg = recommendImg;
    }

    @Basic
    @Column(name = "recommendWord")
    public String getRecommendWord() {
        return recommendWord;
    }

    public void setRecommendWord(String recommendWord) {
        this.recommendWord = recommendWord;
    }

    @Basic
    @Column(name = "tinyTable")
    public int getTinyTable() {
        return tinyTable;
    }

    public void setTinyTable(int tinyTable) {
        this.tinyTable = tinyTable;
    }

    @Basic
    @Column(name = "middleTable")
    public int getMiddleTable() {
        return middleTable;
    }

    public void setMiddleTable(int middleTable) {
        this.middleTable = middleTable;
    }

    @Basic
    @Column(name = "bigTable")
    public int getBigTable() {
        return bigTable;
    }

    public void setBigTable(int bigTable) {
        this.bigTable = bigTable;
    }

    @Basic
    @Column(name = "longitude")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String lognitude) {
        this.longitude = lognitude;
    }

    @Basic
    @Column(name = "latitude")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (bigTable != that.bigTable) return false;
        if (consumption != that.consumption) return false;
        if (expected != that.expected) return false;
        if (id != that.id) return false;
        if (middleTable != that.middleTable) return false;
        if (stars != that.stars) return false;
        if (tinyTable != that.tinyTable) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (recommendImg != null ? !recommendImg.equals(that.recommendImg) : that.recommendImg != null) return false;
        if (recommendWord != null ? !recommendWord.equals(that.recommendWord) : that.recommendWord != null)
            return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + consumption;
        result = 31 * result + stars;
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + expected;
        result = 31 * result + (recommendImg != null ? recommendImg.hashCode() : 0);
        result = 31 * result + (recommendWord != null ? recommendWord.hashCode() : 0);
        result = 31 * result + tinyTable;
        result = 31 * result + middleTable;
        result = 31 * result + bigTable;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        return result;
    }
}
