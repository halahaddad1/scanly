package com.scanly.app.ShoppingListProduct;

import com.scanly.app.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingListProductController {

    @Autowired
    FirebaseService firebaseService;
//    @GetMapping("/getShoppingListProductDetails")
//    public ShoppingListProduct getShoppingListProduct(@RequestParam(required = false) String name) throws InterruptedException, ExecutionException {
//        return firebaseService.getShoppingListProductDetails(name);
//    }
//
//    @PutMapping("/updatehoppingListProduct")
//    public String updateProduct(@RequestBody ShoppingListProduct product ) throws InterruptedException, ExecutionException {
//        return firebaseService.updateShoppingListProductDetails(product);
//    }

}
