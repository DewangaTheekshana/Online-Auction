package lk.jiat.ee.ejb.bean;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import lk.jiat.ee.core.model.Bid;
import lk.jiat.ee.core.websocket.BidBroadcaster;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/MyTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic")
})
public class BidNotificationBean implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            // Extract the Bid object from the message
            if (message instanceof ObjectMessage) {
                ObjectMessage objMsg = (ObjectMessage) message;
                Object obj = objMsg.getObject();

                if (obj instanceof Bid) {
                    Bid bid = (Bid) obj;

                    System.out.println("Bid: " + bid.getUserId());

                    // Create a simple message to send to clients
                    String notification = String.format(
                            "{\"productId\": %d, \"bidAmount\": %.2f, \"userId\": %d}",
                            bid.getProductId(), bid.getBidAmount(), bid.getUserId()
                    );

                    System.out.println("Broadcasting: " + notification);

                    // Broadcast to all connected WebSocket clients
                    BidBroadcaster.broadcast(notification);
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
