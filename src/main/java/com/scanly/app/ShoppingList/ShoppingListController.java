package com.scanly.app.ShoppingList;

import com.scanly.app.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;

@RestController
public class ShoppingListController {

    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/getListDetails")
    public ShoppingList getList(@RequestParam String name) throws InterruptedException, ExecutionException {
        return firebaseService.getListDetails(name);
    }

    @PostMapping("/createList")
    public String createList(@RequestBody ShoppingList shoppingList) throws InterruptedException, ExecutionException {
        return firebaseService.saveListDetails(shoppingList);
    }

    @PutMapping("/updateList")
    public String updateList(@RequestBody ShoppingList shoppingList ) throws InterruptedException, ExecutionException {
        return firebaseService.updateListDetails(shoppingList);
    }

    @DeleteMapping("/deleteList")
    public String deleteList(@RequestParam String name){

        return firebaseService.deleteList(name);
    }
}
