package com.scanly.app.Receipt;

import com.scanly.app.QuoteOfTheDay.QuoteOfTheDay;
import com.scanly.app.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class ReceiptController {

    @Autowired
    FirebaseService firebaseService;

    @PostMapping(value = "/ocrImage")
    public void klippaImage(@RequestParam("file") MultipartFile file) throws IOException {
        QuoteOfTheDay quote = new QuoteOfTheDay();
        quote.klippaMultiPartPostRequest(file.getBytes());

        // passing the filepath to the service method
//        Object ocrDataImage = firebaseService.klippaImage(fileName);
//        return Response.ResponseBuilder.Build();

    }


        @GetMapping("/getReceiptDetails")
        public Receipt getReceipt(@RequestParam(required = false) String name) throws InterruptedException, ExecutionException {
            return firebaseService.getReceiptDetails(name);
        }

        @PostMapping("/createReceipt")
        public String createReceipt(@RequestBody Receipt receipt) throws InterruptedException, ExecutionException {
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

