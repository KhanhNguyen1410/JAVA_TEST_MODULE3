package service.product;

import model.Product;

import java.util.List;

public interface MyProductService {
    List<Product> findAll();

    void insert(Product product);

    void update(Product product);

    void delete(int id);
    Product findById(int id);
    List<Product> searchByName(String name);
}
