package com.scanly.app.Product;

import com.scanly.app.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class ProductController {

    @Autowired
    FirebaseService firebaseService;
    @GetMapping("/getProductDetails")
    public Product getProduct(@RequestParam(required = false) String name) throws InterruptedException, ExecutionException {
        return firebaseService.getProductDetails(name);
    }

    @PostMapping("/createProduct")
    public String createProduct(@RequestBody Product product) throws InterruptedException, ExecutionException {
        return firebaseService.saveProductDetails(product);
    }

    @PutMapping("/updateProduct")
    public String updateProduct(@RequestBody Product product ) throws InterruptedException, ExecutionException {
        return firebaseService.updateProductDetails(product);
    }

    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestParam String name){
        return firebaseService.deleteProduct(name);
    }
}
