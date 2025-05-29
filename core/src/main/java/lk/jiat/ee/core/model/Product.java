package lk.jiat.ee.core.model;

import java.util.ArrayList;
import java.util.Date;

public class Product {

    private int id;
    private String name;
    private String image;
    private double basePrice;
    private Date endTime;

    public Product(int id, String name, String image, double basePrice, Date endTime) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.basePrice = basePrice;
        this.endTime = endTime;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}