package com.scanly.app.UsersController;



import com.scanly.app.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class UsersController {



        @Autowired
        UserService userService;

        @GetMapping("/getUserDetails")
        public User getUser(@RequestParam String name ) throws InterruptedException, ExecutionException{
            return userService.getUserDetails(name);
        }

        @PostMapping("/createUser")
        public String createPatient(@RequestBody User user ) throws InterruptedException, ExecutionException {
            return userService.saveUserDetails(user);
        }

        @PutMapping("/updateUser")
        public String updatePatient(@RequestBody User user  ) throws InterruptedException, ExecutionException {
            return userService.updateUserDetails(user);
        }

        @DeleteMapping("/deleteUser")
        public String deleteUser(@RequestParam String name){
            return userService.deleteUser(name);
        }
}
