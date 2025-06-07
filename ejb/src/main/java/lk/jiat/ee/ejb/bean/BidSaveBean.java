package lk.jiat.ee.ejb.bean;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
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

        if (bidAmount > product.getMaxBid()) {
            product.setMaxBid(bidAmount);
            product.getBidPlacedEvent().add(new Bid(productId, bidAmount, userId));
            dataStorage.replaceProducts(product);
        } else {
            System.out.println("Bid not higher than current MaxBid.");
        }


        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(topic, new Bid(productId, bidAmount, userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
