package com.scanly.app.Receipt;

import com.scanly.app.KlippaApiCall.KlippaApiCall;
import com.scanly.app.User.User;
import com.scanly.app.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

@RestController
public class ReceiptController {

    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/ocrImage")
    public void klippaImage(@RequestParam("file") MultipartFile file, String name ) throws IOException, ExecutionException, InterruptedException, ParseException {
        KlippaApiCall quote = new KlippaApiCall();
        FirebaseService service = new FirebaseService();
        User user = service.getUserDetails(name);
        quote.klippaMultiPartPostRequest(file.getBytes(),user);

    }

    @GetMapping("/getReceiptDetails")
    public Receipt getReceipt(@RequestParam(required = false) String name) throws InterruptedException, ExecutionException {
        return firebaseService.getReceiptDetails(name);
    }

    @PostMapping("/createReceipt")
    public String createReceipt(@RequestBody Receipt receipt) throws InterruptedException, ExecutionException {
        receipt.toBuilder()
                .name(receipt.getName())
                .createdOn(receipt.getCreatedOn())
                .products(receipt.getProducts())
                .build();
        return firebaseService.saveReceiptDetails(receipt);
    }

    @PutMapping("/updateReceipt")
    public String updateReceipt(@RequestBody Receipt receipt ) throws InterruptedException, ExecutionException {
        return firebaseService.updateReceiptDetails(receipt);
    }

    @DeleteMapping("/deleteReceipt")
    public String deleteReceipt(@RequestParam String name){
        return firebaseService.deleteReceipt(name);
    }
}

