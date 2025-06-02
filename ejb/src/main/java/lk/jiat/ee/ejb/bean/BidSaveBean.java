package lk.jiat.ee.ejb.bean;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lk.jiat.ee.core.model.Bid;
import lk.jiat.ee.core.model.Product;
import lk.jiat.ee.ejb.remote.DataStorage;
import lk.jiat.ee.ejb.remote.RemoteBidSave;

import java.util.ArrayList;

@Stateless
public class BidSaveBean implements RemoteBidSave {

    @EJB
    DataStorage dataStorage;

    @Override
    public void placeBid(Integer productId, double bidAmount, Integer userId) {

        Product product = dataStorage.getProductById(productId);
        ArrayList<Bid> bidArrayList = (ArrayList<Bid>) product.getBidPlacedEvent();

        System.out.println("productID"+productId);
        System.out.println("product"+product.getName());
        System.out.println("bidAmount"+bidAmount);
        System.out.println("userID"+userId);
        System.out.println("bidArrayList"+bidArrayList);

        if (product == null) {
            System.out.println("product is null");
            return;
        }


        product.getBidPlacedEvent().add(new Bid(productId, bidAmount, userId));

        dataStorage.replaceProducts(product);

        System.out.println("Updated product stored. MaxBid: " + product.getMaxBid());

    }
}
