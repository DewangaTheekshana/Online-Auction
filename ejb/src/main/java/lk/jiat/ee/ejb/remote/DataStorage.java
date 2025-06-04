package lk.jiat.ee.ejb.remote;

import jakarta.ejb.Remote;
import lk.jiat.ee.core.model.AutoBid;
import lk.jiat.ee.core.model.Product;
import lk.jiat.ee.core.model.User;

import java.util.List;

@Remote
public interface DataStorage {
    List<User> getUsers();
    List<Product> getProducts();
    Product getProductById(int id);
    User getUserById(int userId);

    void registerAutoBid(AutoBid autoBid);
    List<AutoBid> getAutoBiddersForProduct(int productId);
    void removeAutoBid(Integer productId, Integer userId);

    void replaceProducts(Product updatedProduct);
}
