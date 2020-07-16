package com.scanly.app.UsersController;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.scanly.app.User.User;
import com.scanly.app.service.FirebaseService;
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
        //    query the db for the user name
        //    if available, return list
        //    if new
        return firebaseService.saveUserDetails(user);
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user  ) throws InterruptedException, ExecutionException {
        return firebaseService.updateUserDetails(user);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String name){
        return firebaseService.deleteUser(name);
    }
}

