package lk.jiat.ee.ejb.bean;

import lk.jiat.ee.core.model.Bid;
import lk.jiat.ee.core.model.Product;
import lk.jiat.ee.core.model.User;
import lk.jiat.ee.ejb.remote.DataStorage;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
@Startup
public class DataStorageBean implements DataStorage {

    private List<User> userList;
    private List<Product> productList;

    @PostConstruct
    public void init(){

        userList = new ArrayList<User>();
        productList = new ArrayList<Product>();
        userList.add(new User(1,"kamal Perera","kamal@gmail.com","123"));
        userList.add(new User(2,"nimal Perera","nimal5@gmail.com","456"));

        productList.add(new Product(1,"ZTE Blade A35","https://lasermobile.lk/wp-content/uploads/2024/11/ZTE-BLADE-A35-520x510.jpg",400.00,new Date(System.currentTimeMillis() + 0 * 00 * 60 * 1000), new ArrayList<Bid>()));
        productList.add(new Product(2,"samsung galaxy a06","https://dialcom.lk/wp-content/uploads/lkkj-430x430.jpg",4800.00,new Date(System.currentTimeMillis() + 3 * 60 * 60 * 1000), new ArrayList<Bid>()));

    }

    @Override
    public List<User> getUsers() {
        return userList;
    }

    @Override
    public List<Product> getProducts() {
        return productList;
    }

    @Override
    public Product getProductById(int id) {
        return productList.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    @Override
    public User getUserById(int userId) {
        return userList.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);
    }

    @Override
    public void replaceProducts(Product updatedProduct) {
        productList.removeIf(p -> p.getId() == (updatedProduct.getId()));
        productList.add(updatedProduct);
        System.out.println("data storage bean"+updatedProduct);
    }

}
