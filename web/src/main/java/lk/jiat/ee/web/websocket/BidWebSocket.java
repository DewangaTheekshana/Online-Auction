package lk.jiat.ee.web.websocket;

import jakarta.ejb.EJB;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/bidsocket")
public class BidWebSocket {

    private static BidBroadcaster broadcaster;

    @EJB
    public void setBroadcaster(BidBroadcaster b) {
        broadcaster = b;
    }

    @OnOpen
    public void onOpen(Session session) {
        broadcaster.add(session);
        System.out.println("Client connected: " + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        broadcaster.remove(session);
        System.out.println("Client disconnected: " + session.getId());
    }

    public static void broadcastToAll(String message) {
        if (broadcaster != null) {
            broadcaster.broadcast(message);
        }
    }
}
