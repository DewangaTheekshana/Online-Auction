package lk.jiat.ee.core.model;

import java.io.Serializable;
import java.util.Date;

public class Bid implements Serializable {
    private Integer userId;
    private double bidAmount;
    private Integer productId;

    public Bid() {
    }

    public Bid(Integer userId, double bidAmount, Integer productId) {
        this.userId = userId;
        this.bidAmount = bidAmount;
        this.productId = productId;
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

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
