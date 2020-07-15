package com.scanly.app.service;


import com.google.api.client.http.HttpHeaders;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scanly.app.List.List;
import com.scanly.app.Product.Product;
import com.scanly.app.Receipt.Receipt;
import com.scanly.app.User.User;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
//    FirebaseDatabase db;
//
//    public FirebaseService() throws IOException {
//
//        db = FirebaseDatabase.getInstance();
//    }

//    public FirebaseDatabase getDb() {
//        return db;
//    }

    public String saveUserDetails(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getName()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }

    public User getUserDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        User user = null;

        if(document.exists()) {
            user = document.toObject(User.class);
            return user;
        }else {
            return null;
        }
    }

    public String updateUserDetails(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getName()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("users").document(name).delete();
        return "Document with ID "+name+" has been deleted";
    }


//    public String klippaImage(String path) {
//
//        // getting the file from disk
//        FileSystemResource value = new FileSystemResource(new File(IMG_3666));
//
//        // adding headers to the api
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        headers.set("X-Auth-Key", "Sr730nTff5FuJL0sHvoNGXFcP2dk0M7X");
//
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("file", value);
//
//
//        HttpEntity<MultiValueMap<String, Object>> requestEntity= new HttpEntity<>(body, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.postForEntity("https://custom-ocr.klippa.com/api/v1/parseDocument", requestEntity,
//                String.class).getBody().toString();
//
//        System.out.println(result);
//        return result;
//    }

    public String saveReceiptDetails(Receipt receipt) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("receipts").document(receipt.getName()).set(receipt);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }

    public Receipt getReceiptDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("receipts").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Receipt receipt = null;

        if(document.exists()) {
            receipt = document.toObject(Receipt.class);
            return receipt;
        }else {
            return null;
        }
    }

    public String updateReceiptDetails(Receipt receipt) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("receipts").document(receipt.getName()).set(receipt);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteReceipt(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("receipts").document(name).delete();
        return "Document with ID "+name+" has been deleted";
    }

    public String saveListDetails(List list) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("lists").document(list.getName()).set(list);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }

    public List getListDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("lists").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        List list = null;

        if(document.exists()) {
            list = document.toObject(List.class);
            return list;
        }else {
            return null;
        }
    }

    public String updateListDetails(List list) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("lists").document(list.getName()).set(list);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteList(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("lists").document(name).delete();
        return "Document with ID "+name+" has been deleted";
    }

    public String saveProductDetails(Product product) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("products").document(product.getName()).set(product);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }

    public Product getProductDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("products").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Product product = null;

        if(document.exists()) {
            product = document.toObject(Product.class);
            return product;
        }else {
            return null;
        }
    }

    public String updateProductDetails(Product product) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("products").document(product.getName()).set(product);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteProduct(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("products").document(name).delete();
        return "Document with ID "+name+" has been deleted";
    }

}


