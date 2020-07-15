package com.scanly.app.List;

import com.scanly.app.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;

@RestController
public class ListController {

    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/getListDetails")
    public List getList(@RequestParam String name) throws InterruptedException, ExecutionException {
        return firebaseService.getListDetails(name);
    }

    @PostMapping("/createList")
    public String createList(@RequestBody List list) throws InterruptedException, ExecutionException {
        return firebaseService.saveListDetails(list);
    }

    @PutMapping("/updateList")
    public String updateList(@RequestBody List list ) throws InterruptedException, ExecutionException {
        return firebaseService.updateListDetails(list);
    }

    @DeleteMapping("/deleteList")
    public String deleteList(@RequestParam String name){

        return firebaseService.deleteList(name);
    }
}
