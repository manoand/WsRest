package wsapp.service;

import wsapp.entity.Product;

import java.util.List;

public interface ProductService {
    boolean insert(Product product);
    boolean delete(long id);
    Product getProduct(long id);
    List<Product> getAll();
    boolean updateProduct(Product product);
}
