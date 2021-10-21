package wsapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wsapp.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wsapp.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product/controller/")
public class ProductController {
    Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping(value = "getDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductDetails() {
        List<Product> listProduct = new ArrayList<Product>(productService.getAll());
        return new ResponseEntity<List<Product>>(listProduct, HttpStatus.OK);
    }

    @PostMapping(value = "addProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.insert(product);
        long count = product.getId();
        LOGGER.info("Product added successfully with id:" + count);
        return new ResponseEntity<String>(String.valueOf(count), HttpStatus.CREATED);
    }

    @GetMapping(value = "getProduct/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductDetails(@PathVariable String id) {
        Product product = productService.getProduct(Integer.valueOf(id));
        if(product == null){
            return new ResponseEntity<Product>(product, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }


    @PutMapping(value = "updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        if (productService.getProduct(product.getId()) == null) {
            Product product2 = null;
            return new ResponseEntity<Product>(product2, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        productService.updateProduct(product);
        Product updated = productService.getProduct(product.getId());
        return new ResponseEntity<Product>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "deleteProduct/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int myId) {
        Product product2 = productService.getProduct(myId);
        if (product2 == null) {
            return new ResponseEntity<Product>(product2, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!productService.delete(myId)) {
            return new ResponseEntity<Product>(product2, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Product>(product2, HttpStatus.OK);
    }


}
