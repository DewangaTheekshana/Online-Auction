package lk.jiat.ee.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface RemoteBidSave {

    void placeBid (Integer productId, double bidAmount, Integer userId);

}
