package dao;

import wsapp.dao.impl.ProductDaoImpl;
import wsapp.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductDaoImplTest {

    @InjectMocks
    private ProductDaoImpl productDao ;

    @Test
    public void testInsert(){
        Product product = new Product("P4","",Double.valueOf("0.56"));
        assertTrue(productDao.insert(product));
    }

    @Test
    public void testDelete(){
        Product product = new Product("P1","",Double.valueOf("1.56"));
        productDao.insert(product);
        assertTrue(productDao.delete(product.getId()));
    }

    @Test
    public void testFind(){
        Product initProduct = new Product("P4","",Double.valueOf("5.56"));
        productDao.insert(new Product("P3","",Double.valueOf("2.56")));
        productDao.insert(initProduct);
        productDao.insert(new Product("P2","",Double.valueOf("1.56")));

        Product finalProduct = productDao.getProduct(initProduct.getId());

        assertEquals(finalProduct,initProduct);
    }

    @Test
    public void testUpdate(){
        Product initProduct = new Product("P4","",Double.valueOf("5.56"));
        productDao.insert(initProduct);
        initProduct.setDescription("Non nul");
        productDao.updateProduct(initProduct);
        assertEquals("Non nul",productDao.getProduct(initProduct.getId()).getDescription());

    }

    @Test
    public void testgetAll(){
        productDao.insert(new Product("P3","",Double.valueOf("2.56")));
        productDao.insert(new Product("P4","",Double.valueOf("5.56")));
        productDao.insert(new Product("P2","",Double.valueOf("1.56")));

        assertEquals(3,productDao.getAll().size());
    }
}