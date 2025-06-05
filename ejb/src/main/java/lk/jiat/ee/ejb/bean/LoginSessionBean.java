package lk.jiat.ee.ejb.bean;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lk.jiat.ee.core.model.User;
import lk.jiat.ee.ejb.remote.DataStorage;
import lk.jiat.ee.ejb.remote.RemoteLogin;

import java.util.List;

@Stateless
public class LoginSessionBean implements RemoteLogin {

    @EJB
    DataStorage dataStorage;

    @Override
    public boolean validate(String email, String password) {
        List<User> UserList = dataStorage.getUsers();
        return UserList.stream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    @Override
    public User findUser(String email) {
        List<User> UserList = dataStorage.getUsers();
        return UserList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
