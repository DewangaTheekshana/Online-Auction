package lk.jiat.ee.core.model;

import java.io.Serializable;
import java.util.Date;

public class AutoBid implements Serializable {
    private Integer userId;
    private Integer productId;
    private double maxBid;
    private Date registeredAt;
    private double lastBidPlaced;

    public AutoBid(Integer userId, Integer productId, double maxBid, Date registeredAt, double lastBidPlaced) {
        this.userId = userId;
        this.productId = productId;
        this.maxBid = maxBid;
        this.registeredAt = registeredAt;
        this.lastBidPlaced = lastBidPlaced;
    }

    public AutoBid() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public double getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(double maxBid) {
        this.maxBid = maxBid;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public double getLastBidPlaced() {
        return lastBidPlaced;
    }

    public void setLastBidPlaced(double lastBidPlaced) {
        this.lastBidPlaced = lastBidPlaced;
    }
}
