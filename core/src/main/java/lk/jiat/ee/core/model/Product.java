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
    private double maxBid;
    private final ArrayList<Bid> bidPlacedEvent;

    public Product(int id, String name, String image, double basePrice, Date endTime, double maxBid, ArrayList<Bid> bidPlacedEvent) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.basePrice = basePrice;
        this.endTime = endTime;
        this.maxBid = maxBid;
        this.bidPlacedEvent = bidPlacedEvent;
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

    public double getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(double maxBid) {
        this.maxBid = maxBid;
    }

    public ArrayList<Bid> getBidPlacedEvent() {
        return bidPlacedEvent;
    }
}