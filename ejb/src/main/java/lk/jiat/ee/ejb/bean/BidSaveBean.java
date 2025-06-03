package lk.jiat.ee.ejb.bean;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.jms.*;
import lk.jiat.ee.core.model.Bid;
import lk.jiat.ee.core.model.Product;
import lk.jiat.ee.ejb.remote.DataStorage;
import lk.jiat.ee.ejb.remote.RemoteBidSave;

import java.util.ArrayList;

@Stateless
public class BidSaveBean implements RemoteBidSave {

    @EJB
    DataStorage dataStorage;

    @Resource(lookup = "jms/MyConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/MyTopic")
    private Topic topic;

    @Override
    public void placeBid(Integer productId, double bidAmount, Integer userId) {
        Product product = dataStorage.getProductById(productId);

        if (product == null) {
            System.out.println("Product is null for ID: " + productId);
            return;
        }

        System.out.println("productID: " + productId);
        System.out.println("product: " + product.getName());
        System.out.println("bidAmount: " + bidAmount);
        System.out.println("userID: " + userId);
        System.out.println("Current MaxBid: " + product.getMaxBid());

        // Only update maxBid if this bid is higher
        if (bidAmount > product.getMaxBid()) {
            product.setMaxBid(bidAmount);
            System.out.println("New MaxBid set: " + bidAmount);
        } else {
            System.out.println("Bid not higher than current MaxBid.");
        }

        // Add the new bid
        product.getBidPlacedEvent().add(new Bid(productId, bidAmount, userId));

        // Persist the updated product
        dataStorage.replaceProducts(product);
        System.out.println("Updated product stored. MaxBid: " + product.getMaxBid());

        // Send JMS message
        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(topic, new Bid(productId, bidAmount, userId));
            System.out.println("Bid broadcasted via JMS." + userId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to broadcast bid.");
        }
    }


}
