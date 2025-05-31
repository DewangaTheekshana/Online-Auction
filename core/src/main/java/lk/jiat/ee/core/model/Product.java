package lk.jiat.ee.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product {

    private int id;
    private String name;
    private String image;
    private double basePrice;
    private Date endTime;

    private final ArrayList<Bid> bidPlacedEvent;

    public Product(ArrayList<Bid> bidPlacedEvent, Date endTime, double basePrice, String image, String name, int id) {
        this.bidPlacedEvent = bidPlacedEvent;
        this.endTime = endTime;
        this.basePrice = basePrice;
        this.image = image;
        this.name = name;
        this.id = id;
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

    public List<Bid> getBidPlacedEvent() {
        return bidPlacedEvent;
    }
}