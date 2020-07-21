package com.scanly.app.User;

import com.scanly.app.service.FirebaseService;
import com.scanly.app.utilities.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class UsersController {

    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/getUserDetails")
    public User getUser(@RequestParam (required = false) String name) throws InterruptedException, ExecutionException{
        return firebaseService.getUserDetails(name);
    }

    @GetMapping("/allUsers")
    public List<User> findAllUsers() throws Exception {
        return firebaseService.findAllUsers();
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        User foundUser = firebaseService.getUserDetails(user.getName());
        if (foundUser == null) {
        //    query the db for the user name
        //    if available, return list
        //    if new
        user.toBuilder()
                .name(user.getName())
                .receipts(user.getReceipts())
                .shoppingList(user.getShoppingList())
                .ProductRecommendations(user.getProductRecommendations())
                .build();
        return firebaseService.saveUserDetails(user);
        } else {
            return foundUser.getName();
        }
    }

////    @PostMapping("/createNewUser")
//    public String createNewUser(@RequestBody String userName) throws InterruptedException, ExecutionException {
//       User createUser = new User(userName);
//       createUser.toBuilder()
//            .name(userName)
//            .receipts(createUser.getReceipts())
//            .shoppingList(createUser.getShoppingList())
//            .build();
//        return firebaseService.saveUserDetails(createUser);
//    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user  ) throws InterruptedException, ExecutionException {
        return firebaseService.updateUserDetails(user);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String name){
        return firebaseService.deleteUser(name);
    }

    @GetMapping("/getShoppingList")
    public JsonMessage<List> getShoppingList(@RequestParam String name) throws InterruptedException, ExecutionException{
        return new JsonMessage<>(firebaseService.findShoppingList(name));
    }

    @GetMapping("/getShoppingRecommendations")
    public JsonMessage<List> getRecommendationsList(@RequestParam User user) throws InterruptedException, ExecutionException{
        return new JsonMessage<>(firebaseService.getRecommendationsList(user.getName()));
    }
}

