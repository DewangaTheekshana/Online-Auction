package lk.jiat.ee.ejb.remote;

import jakarta.ejb.Remote;
import lk.jiat.ee.core.model.User;

@Remote
public interface RemoteLogin {

    boolean validate(String email, String password);

    User findUser(String email);

}
