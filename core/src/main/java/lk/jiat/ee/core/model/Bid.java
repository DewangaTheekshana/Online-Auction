package lk.jiat.ee.core.model;

import java.io.Serializable;
import java.util.Date;

public class Bid implements Serializable {
    private int userId;
    private double amount;
    private Date time;

    public Bid() {
    }

    public Bid(int userId, double amount, Date time) {
        this.userId = userId;
        this.amount = amount;
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
