package lk.jiat.ee.core.websocket;

import jakarta.websocket.Session;
import java.util.concurrent.CopyOnWriteArraySet;

public class BidBroadcaster {

    private static final CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    public void add(Session session) {
        sessions.add(session);
    }

    public void remove(Session session) {
        sessions.remove(session);
    }

    public static void broadcast(String message) {
        for (Session session : sessions) {
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(message);
            }
        }
    }
}
