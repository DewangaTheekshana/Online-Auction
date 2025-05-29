package lk.jiat.ee.ejb.remote;

import jakarta.ejb.Remote;
import lk.jiat.ee.core.model.Product;
import lk.jiat.ee.core.model.User;

import java.util.List;

@Remote
public interface DataStorage {
    List<User> getUsers();
    List<Product> getProducts();
    Product getProductById(int id);
}
