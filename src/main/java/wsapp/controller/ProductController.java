package wsapp.controller;

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
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product/controller/getDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductDetails() {
        List<Product> listProduct = new ArrayList<Product>(productService.getAll());
        return new ResponseEntity<List<Product>>(listProduct, HttpStatus.OK);
    }

    @PostMapping(value = "/product/controller/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.insert(product);
        long count = product.getId();
        return new ResponseEntity<String>("Product added successfully with id:" + count, HttpStatus.CREATED);

    }

    @PutMapping(value = "/product/controller/updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        if (productService.getProduct(product.getId()) == null) {
            Product product2 = null;
            return new ResponseEntity<Product>(product2, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        productService.updateProduct(product);
        Product updated = productService.getProduct(product.getId());
        return new ResponseEntity<Product>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/product/controller/deleteProduct/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
