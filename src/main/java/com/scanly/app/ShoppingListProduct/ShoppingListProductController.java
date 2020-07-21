package com.scanly.app.ShoppingListProduct;

import com.scanly.app.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class ShoppingListProductController {

//    @Autowired
//    FirebaseService firebaseService;
//    @GetMapping("/getShoppingListProductDetails")
//    public ShoppingListProduct getShoppingListProduct(@RequestParam(required = false) String name) throws InterruptedException, ExecutionException {
//        return firebaseService.getShoppingListProductDetails(name,product);
//    }
//
//    @PutMapping("/updatehoppingListProduct")
//    public String updateProduct(@RequestBody ShoppingListProduct product ) throws InterruptedException, ExecutionException {
//        return firebaseService.updateShoppingListProductDetails(product);
//    }

}
