package lk.jiat.ee.core.model;

import java.io.Serializable;
import java.util.Date;

public class Bid implements Serializable {
    private Integer userId;
    private double bidAmount;
    private Integer productId;
    private Date timestamp = new Date();

    public Bid() {
    }

    public Bid(Integer productId, double bidAmount, Integer userId) {
        this.productId = productId;
        this.bidAmount = bidAmount;
        this.userId = userId;
        this.timestamp = new Date();
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Integer getProductId() {
        return productId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
