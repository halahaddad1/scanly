package com.scanly.app.Receipt;

import com.scanly.app.KlippaApiCall.KlippaApiCall;
import com.scanly.app.User.User;
import com.scanly.app.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;



//@Configuration
//@PropertySource(value = "classpath:src/main/resources/application.properties")
@RestController
public class ReceiptController {

    @Autowired
    FirebaseService firebaseService;

    @Value("${KLIPPA_AUTH}")
    private String KLIPPA_AUTH;


    @PostMapping("/ocrImage")
    public List<String> klippaImage(@RequestParam("file") MultipartFile file, String name ) throws IOException, ExecutionException, InterruptedException, ParseException {
        KlippaApiCall klippa = new KlippaApiCall();
        FirebaseService service = new FirebaseService();
        User user;
        if (service.getUserDetails(name) == null ) {
            user = firebaseService.getUserDetails(name);
        } else {
            user = service.getUserDetails(name);
        }

        List<String> receiptItems = klippa.klippaMultiPartPostRequest(file.getBytes(), user, KLIPPA_AUTH);
//            return the status code to flutter
        return receiptItems;
    }

//    @PutMapping("/ocrImage")
//    public void updateKlippaImage(@RequestParam("file") MultipartFile file, String name ) throws IOException, ExecutionException, InterruptedException, ParseException {
//        KlippaApiCall klippa = new KlippaApiCall();
//        FirebaseService service = new FirebaseService();
//        User user = service.getUserDetails(name);
//        klippa.klippaMultiPartPostRequest(file.getBytes(), user);
//            return the status code to flutter
//    }

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

