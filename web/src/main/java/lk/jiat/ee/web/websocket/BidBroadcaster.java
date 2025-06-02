package lk.jiat.ee.web.websocket;

import jakarta.ejb.Singleton;
import jakarta.ejb.LocalBean;
import java.util.concurrent.CopyOnWriteArraySet;
import jakarta.websocket.Session;

@Singleton
public class BidBroadcaster {

    private final CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    public void add(Session session) {
        sessions.add(session);
    }

    public void remove(Session session) {
        sessions.remove(session);
    }

    public void broadcast(String message) {
        for (Session session : sessions) {
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(message);
            }
        }
    }
}
