package wsapp.dao.impl;

import wsapp.dao.ProductDao;
import wsapp.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ProductDaoImpl implements ProductDao {
    private static final List<Product> proList = new ArrayList<Product>();
    private static long index=0;

    @Override
    public boolean insert(Product product) {
        product.setId(index++);
        proList.add(product);
        return proList.contains(product);
    }

    @Override
    public boolean delete(long id) {
        Predicate<Product> predi = p -> p.getId() == id;
        return proList.removeIf(predi);
    }

    @Override
    public Product getProduct(long id) {
        Optional<Product> op = proList.stream().filter(product -> product.getId() == id).findFirst();
        return op.isPresent() ? op.get() : null ;
    }

    @Override
    public List<Product> getAll() {
        return proList;
    }

    @Override
    public boolean updateProduct(Product product) {
        Product pInList = getProduct(product.getId());
        boolean isPresent=pInList !=null;
        if(isPresent) {
            pInList.setName(product.getName());
            pInList.setPrice(product.getPrice());
            pInList.setDescription(product.getDescription());
        }
        return isPresent;
    }


}
