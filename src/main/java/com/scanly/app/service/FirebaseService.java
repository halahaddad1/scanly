package com.scanly.app.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.scanly.app.CanonicalProducts.CanonicalProduct;
import com.scanly.app.Product.Product;
import com.scanly.app.Receipt.Receipt;
import com.scanly.app.ShoppingList.ShoppingList;
import com.scanly.app.ShoppingListProduct.ShoppingListProduct;
import com.scanly.app.User.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    public String saveUserDetails(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getName()).set(user);
        // please return the username!
        collectionsApiFuture.get();
        return this.getUserDetails(user.getName()).getName();
//        return collectionsApiFuture.get().getUpdateTime().toString();
    }


    public User getUserDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        User user = null;

        if (document.exists()) {
            user = document.toObject(User.class);
            return user;
        } else {
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
        return "Document with ID " + name + " has been deleted";
    }

    public List<User> findAllUsers() throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").get();
// future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId()); //+ " => " + document.toObject(User.class));
//            /* Specify the size of the list up front to prevent resizing. */
        }

        List<User> userList = new ArrayList<>(documents.size());
        for (QueryDocumentSnapshot document : documents) {
            if (document.exists()) {
//                user = document.toObject(User.class);
                userList.add(document.toObject(User.class));
            }
        }
        return userList;
    }

    //    public List<Product> findShoppingList(String name) throws ExecutionException, InterruptedException {
    public List<ShoppingListProduct> findShoppingList(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        User user = null;

        if (document.exists()) {
            user = document.toObject(User.class);
            List<ShoppingListProduct> shoppingList = user.getShoppingList().getShoppingItems();
            // ShoppingListProduct item = user.getShoppingList().getShoppingItems();
            for (ShoppingListProduct item : shoppingList) {
                if (item.timeToBuy()) {
                    item.setShowOnList(true);
                }
            }
            this.updateUserDetails(user);
            return user.getShoppingList().getShoppingItems();
        } else {
            return null;
        }
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

        if (document.exists()) {
            receipt = document.toObject(Receipt.class);
            return receipt;
        } else {
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
        return "Document with ID " + name + " has been deleted";
    }

    public String saveListDetails(ShoppingList shoppingList) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("lists").document(shoppingList.getName()).set(shoppingList);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }

    public ShoppingList getListDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("lists").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        ShoppingList shoppingList = null;

        if (document.exists()) {
            shoppingList = document.toObject(ShoppingList.class);
            return shoppingList;
        } else {
            return null;
        }
    }

    public String updateListDetails(ShoppingList shoppingList) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("lists").document(shoppingList.getName()).set(shoppingList);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteList(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("lists").document(name).delete();
        return "Document with ID " + name + " has been deleted";
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

        if (document.exists()) {
            product = document.toObject(Product.class);
            return product;
        } else {
            return null;
        }


    }

    // not using this
//    public ShoppingListProduct getShoppingListProductDetails(String name) throws InterruptedException, ExecutionException {
//        Firestore dbFirestore = FirestoreClient.getFirestore();
//        DocumentReference documentReference = dbFirestore.collection("shoppingList").document(name);
//        ApiFuture<DocumentSnapshot> future = documentReference.get();
//
//        DocumentSnapshot document = future.get();
//
//        ShoppingListProduct product = null;
//
//        if(document.exists()) {
//            product = document.toObject(ShoppingListProduct.class);
//            return product;
//        }else {
//            return null;
//        }
//
//    }

    public String updateProductDetails(Product product) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("products").document(product.getName()).set(product);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    // not using this
//    public String updateShoppingListProductDetails(ShoppingListProduct product) throws InterruptedException, ExecutionException {
//        Firestore dbFirestore = FirestoreClient.getFirestore();
//        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("shoppingList").document(product.getName()).set(product);
//        return collectionsApiFuture.get().getUpdateTime().toString();
//    }

    public String deleteProduct(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("products").document(name).delete();
        return "Document with ID " + name + " has been deleted";
    }

    public String saveCanonicalProductDetails(CanonicalProduct canonicalProduct) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("canonical-products").document(canonicalProduct.getSeedName()).set(canonicalProduct);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }

    public CanonicalProduct getCanonicalProductDetails(String seedName) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("canonical-products").document(seedName);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        CanonicalProduct canonicalProduct = null;

        if (document.exists()) {
            canonicalProduct = document.toObject(CanonicalProduct.class);
            return canonicalProduct;
        } else {
            return null;
        }
    }

    public ShoppingListProduct getShoppingListProductDetails(String name, String product) throws InterruptedException, ExecutionException {
        User user = this.getUserDetails(name);
        for (ShoppingListProduct productO : user.getShoppingList().getShoppingItems()) {
            if (productO.getName().equals(product)) {
                return productO;
            } else {
                continue;
            }
        }
        return null;
    }

    public List<Product> getRecommendationsList(String name) throws ExecutionException, InterruptedException {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection("users").document(name);
            ApiFuture<DocumentSnapshot> future = documentReference.get();

            DocumentSnapshot document = future.get();
            if (document.exists()) {
                User user = this.getUserDetails(name);
                user.setProductRecommendations();
                this.saveUserDetails(user);
                return user.getProductRecommendations();
            } else {
                User newUser = new User(name);
                newUser.toBuilder()
                        .name(newUser.getName())
                        .receipts(newUser.getReceipts())
                        .shoppingList(newUser.getShoppingList())
                        .ProductRecommendations(newUser.getProductRecommendations())
                        .build();
                this.saveUserDetails(newUser);
                User user = this.getUserDetails(name);
                user.setProductRecommendations();
                this.saveUserDetails(user);
                return user.getProductRecommendations();
            }
        } catch (ExecutionException e) {
            return null;
        }

    }

    public String updateRecommendationProduct(String user, String product) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
//        Iterable<DocumentReference> documentReferences = dbFirestore.collection("users").document(user).collection("productRecommendations").listDocuments();
//        Query query = dbFirestore.collection("users")
//                .document(user)
//                .collection("productRecommendations")
//                .whereEqualTo("name", product);
//
//        CollectionReference userCollection = dbFirestore.collection("users");
//        ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
//        return querySnapshotApiFuture.toString() ;

        // TODO: kat's suggestions
        // get user and make Java user
        // get product Recommandation list for java user
        // save original product recommandation to a new variable
        // remove the product from the recommendation list
        // query with two where clauses, for user name and productRecommendations that equals the original map, set with new array
        //


//        ApiFuture<WriteResult> writeResult = dbFirestore.collection("users").document(user).collection("productRecommendations").document().delete();

//        dbFirestore.collection("users").whereEqualTo("name", user).whereEqualTo("productRecommendations", oldProductList)).set(user.getProductRecommendations());
//        QuerySnapshot productName = collection.whereArrayContains("name", product).get().get();
//        System.out.println(productName);

//        WriteResult writeResult1 = writeResult.get();

//        return writeResult.get().toString();
        DocumentReference documentReference = dbFirestore.collection("users").document(user);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
//
        DocumentSnapshot document = future.get();
//
        if (document.exists()) {
            User foundUser = document.toObject(User.class);
            List<Product> productList = foundUser.getProductRecommendations();
            for (Product pItem : productList) {
                if (pItem.getName().equals(product)) {
                    productList.remove(pItem);
                    this.updateUserDetails(foundUser);
                    return pItem.getName() + " was successfully deleted";
                } else {
                    continue;
                }
            }
        } else {
            return "could not find user";
        }
        return "could not find user";
    }

    public String addRecommendationProduct(String user, String product) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(user);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        if (document.exists()) {
            User foundUser = document.toObject(User.class);
            ShoppingList shoppingList = foundUser.getShoppingList();
            shoppingList.addShoppingItemsFromRecommendations(product);
            List<Product> productList = foundUser.getProductRecommendations();
            for (Product pItem : productList) {
                if (product.equals(pItem.getName()) && productList.size() > 1) {
                    productList.remove(pItem);
                    this.updateUserDetails(foundUser);
                    return product + " was successfully deleted and added to shopping list";
                } else if (product.equals(pItem.getName()) && productList.size() == 1) {
                    foundUser.setProductRecommendationsToEmpty(new ArrayList<Product>());
                    this.updateUserDetails(foundUser);
                    return product + " was found alone in the list and whole list was deleted";
                } else {
                    return "could not find " + product + " in your recommendation list";
                }
            }
        }
        return "could not add product!";

        }


        public String deleteProductFromShoppingList (String user, String product) throws
        ExecutionException, InterruptedException {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection("users").document(user);
            ApiFuture<DocumentSnapshot> future = documentReference.get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                User foundUser = document.toObject(User.class);
                ShoppingList productList = foundUser.getShoppingList();
                for (ShoppingListProduct pItem : productList.getShoppingItems()) {
                    if (pItem.getName().equals(product)) {
                        productList.setState(pItem, false);
                        this.updateUserDetails(foundUser);
                        return product + " was successfully deleted";
                    }
                }
            } else {
                return "could not find user";
            }
            return "could not even try to find user";
        }


    public String createShoppingListProduct(String user, String product) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(user);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        if (document.exists()) {
            User foundUser = document.toObject(User.class);
            ShoppingList shoppingList = foundUser.getShoppingList();
            shoppingList.addShoppingItems(product);
            this.updateUserDetails(foundUser);
            return product + " was successfully added to shopping list";
        }
        return "could not add product!";

    }


}


