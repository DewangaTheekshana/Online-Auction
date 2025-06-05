package lk.jiat.ee.ejb.bean;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import lk.jiat.ee.core.model.AutoBid;
import lk.jiat.ee.core.model.Bid;
import lk.jiat.ee.core.model.Validate;
import lk.jiat.ee.core.websocket.BidBroadcaster;
import lk.jiat.ee.ejb.remote.DataStorage;
import lk.jiat.ee.ejb.remote.RemoteBidSave;

import java.util.List;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/MyTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic")
})
public class BidNotificationBean implements MessageListener {

    @EJB
    DataStorage dataStorage;

    @EJB
    RemoteBidSave remoteBidSave;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objMsg = (ObjectMessage) message;
                Object obj = objMsg.getObject();

                if (obj instanceof Bid) {
                    Bid bid = (Bid) obj;

                    String notification = String.format(
                            "{\"productId\": %d, \"bidAmount\": %.2f, \"userId\": %d}",
                            bid.getProductId(), bid.getBidAmount(), bid.getUserId()
                    );

                    BidBroadcaster.broadcast(notification);

                    int userId = bid.getUserId();

                    List<AutoBid> autoBidders = dataStorage.getAutoBiddersForProduct(bid.getProductId());

                    for (AutoBid autoBidder : Validate.sortBid(autoBidders)) {

                        if (userId == autoBidder.getUserId()) continue;
                        System.out.println("Bidder: " + bid.getUserId()+" user"+autoBidder.getUserId());
                        double nextBid = bid.getBidAmount() + 10;

                        if (nextBid <= autoBidder.getMaxBid()){
                            remoteBidSave.placeBid(bid.getProductId(),nextBid, autoBidder.getUserId());
                            break;
                        }

                    }

                } else {
                    System.out.println("Received JMS message is not a Bid instance.");
                }
            } else {
                System.out.println("Received message is not an ObjectMessage.");
            }
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("Error processing bid message.");
        }
    }
}
