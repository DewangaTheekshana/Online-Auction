package lk.jiat.ee.web.websocket;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lk.jiat.ee.core.websocket.BidBroadcaster;

@ServerEndpoint("/bidsocket")
public class BidWebSocket {

    private static final BidBroadcaster broadcaster = new BidBroadcaster();

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
        broadcaster.broadcast(message);
    }
}
