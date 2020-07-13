package com.scanly.app.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.DocumentReference;
import com.scanly.app.List.List;
import com.scanly.app.Product.Product;
import com.scanly.app.Receipt.Receipt;
import com.scanly.app.User.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {


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

    public String updateProductDetails(Product list) throws InterruptedException, ExecutionException {
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


