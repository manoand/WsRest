package wsapp.service.impl;

import wsapp.dao.ProductDao;
import wsapp.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsapp.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public boolean insert(Product product) {
        return productDao.insert(product);
    }

    @Override
    public boolean delete(long id) {
        return productDao.delete(id);
    }

    @Override
    public Product getProduct(long id) {
        return productDao.getProduct(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }
}
