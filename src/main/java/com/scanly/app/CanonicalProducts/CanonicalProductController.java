package com.scanly.app.CanonicalProducts;

import com.scanly.app.Product.Product;
import com.scanly.app.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class CanonicalProductController {


        @Autowired
        FirebaseService firebaseService;
        @GetMapping("/getCanonicalProductDetails")
        public CanonicalProduct getCanonicalProduct(@RequestParam(required = false) String nickname) throws InterruptedException, ExecutionException {
            return firebaseService.getCanonicalProductDetails(nickname);
        }

        @PostMapping("/createCanonicalProduct")
        public String createCanonicalProduct(@RequestBody CanonicalProduct canonicalProduct) throws InterruptedException, ExecutionException {
            canonicalProduct.toBuilder()
                    .name(canonicalProduct.getName())
                    .build();
            return firebaseService.saveCanonicalProductDetails(canonicalProduct);
        }
}
