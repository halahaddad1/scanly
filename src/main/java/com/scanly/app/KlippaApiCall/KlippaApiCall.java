package com.scanly.app.KlippaApiCall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scanly.app.CanonicalProducts.CanonicalProduct;
import com.scanly.app.Product.Product;
import com.scanly.app.Product.ShoppingListProduct;
import com.scanly.app.Receipt.Receipt;
import com.scanly.app.ShoppingList.ShoppingList;
import com.scanly.app.User.User;
import com.scanly.app.service.FirebaseService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

//import com.google.api.client.util.Value;


public class KlippaApiCall {


    public KlippaApiCall() {
    }

    public void request() throws JsonProcessingException, JsonMappingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        String fooResourceUrl = "https://api.imgur.com/3/upload";
        ResponseEntity<String> response = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, entity, String.class);

        System.out.println(response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode contents = root.path("contents").path("quotes").get(0);

        System.out.println("this is the response status code : " + response.getStatusCode());

        System.out.println("this is the root: " + contents);
        System.out.println("this is the name: " + root.path("contents").path("quotes").get(0).path("quote"));

    }

    public void multiPartPostRequest() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpHeaders fileheaders = new HttpHeaders();
        fileheaders.setContentDisposition(ContentDisposition.parse("form-data; name=\"image\"; filename=\"IMG_3666.jpg\""));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setBearerAuth("");
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        FileSystemResource file = new FileSystemResource("IMG_3666.jpg");
//        body.add("image", file );
        HttpEntity<byte[]> entitystream = new HttpEntity<>(file.getInputStream().readAllBytes(), fileheaders);
        body.add("document", entitystream);
        body.add("type", "file");


        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        String imgurResourceUrl = "https://api.imgur.com/3/upload";
        ResponseEntity<String> response = restTemplate.postForEntity(imgurResourceUrl, entity, String.class);

        System.out.println(response.getBody());
//
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(response.getBody());
//        JsonNode contents = root.path("contents").path("quotes").get(0).path("quote");
//
//
//
//        System.out.println("this is the response status code : " + response.getStatusCode());
//
//        System.out.println("this is the root: " + response.getBody());
//        System.out.println("this is the name: " + root.path("contents").path("quotes").get(0).path("quote"));

    }


    public void klippaMultiPartPostRequest(byte[] arr, User user, String KLIPPA_AUTH) throws IOException, ExecutionException, InterruptedException, ParseException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpHeaders fileheaders = new HttpHeaders();
        fileheaders.setContentDisposition(ContentDisposition.parse("form-data; name=\"document\"; filename=\"scan.jpg\""));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        headers.set("X-Auth-Key" , KLIPPA_AUTH);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
//        FileSystemResource file = new FileSystemResource("IMG_3666.jpg");
//        body.add("image", file );
        HttpEntity<byte[]> entitystream = new HttpEntity<>(arr, fileheaders);
        body.add("document", entitystream);
//        body.add( "type", "file" );


        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        String klippaResourceUrl = "https://custom-ocr.klippa.com/api/v1/parseDocument";
        ResponseEntity<String> response = restTemplate.postForEntity(klippaResourceUrl, entity, String.class);

//        System.out.println(response.getBody());


        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Receipt receipt = mapper.readValue(response.getBody(), Receipt.class);
        JsonNode root = mapper.readTree(response.getBody());


//        toPrettyString() -> prints like text
//        toString() -> returns JSON string
//        asText() -> actual String
        FirebaseService service = new FirebaseService();
        if (user.getReceipts() == null){
            service.saveUserDetails(user);
//            user.setReceipts();
//            user.getShoppingList(user.getName());
        }
        // HALA do we need the next line here or at the end of the method only?
        service.updateUserDetails(user);


//        User user = service.getUserDetails(userName);

        JsonNode name = root.path("data").path("date");
        String prettyStringName = name.toPrettyString();
        String createdOn = root.path("data").path("purchasedate").asText();
//        String prettyStringCreatedOn = createdOn.toPrettyString();
        JsonNode products = root.path("data").path("lines").get(0).path("lineitems");
        System.out.println("this is the root: " + response.getBody());

//        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
//        Date date=formatter1.parse(createdOn.asText());
//
//
//        DateFormat outputFormat = new SimpleDateFormat("MM/yyyy", Locale.US);
//        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
//
//        String inputText = "2012-11-17T00:00:00.000-05:00";
//        Date date = inputFormat.parse(inputText);
//        String outputText = outputFormat.format(date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date currentDate = sdf.parse(createdOn);

        SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
        Date receiptDate = sdfIn.parse(createdOn);


        Receipt userReceipt = new Receipt(name.asText(), receiptDate);
        
        user.addReceiptObject(userReceipt);



//        Stream.of(products).map(product -> Product.builder()
//                                                  .name(product.path("title").asText())
//                                                  .build())
//                .forEach(newProduct -> {
//                    userReceipt.addProductObject(newProduct);
//                    try {
//                        service.updateProductDetails(newProduct);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        service.updateReceiptDetails(userReceipt);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        service.updateUserDetails(user);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                });
        ShoppingList shoppingList = user.getShoppingList(user.getName());

//        Stream.of(products).map(Product::toBuilder)
        for (JsonNode product : products) {
            String title = product.path("title").asText().toLowerCase();
            if (title.contains("/")) {
                title = title.replace("/", "");
            }
            CanonicalProduct canonical = service.getCanonicalProductDetails(title);
            if ( canonical == null ){
               continue;
            } else {
                String canonicalName = canonical.getCanonicalName();
                Product receiptProduct = new Product(canonicalName);
                userReceipt.addProductObject(receiptProduct);
                service.updateReceiptDetails(userReceipt);

                ShoppingListProduct listProduct = new ShoppingListProduct(canonicalName, receiptDate);
                shoppingList.addShoppingItems(listProduct);

//                ShoppingListProduct listProduct;
//                for (ShoppingListProduct pastProduct : shoppingList.getShoppingItems()) {
//                    if (pastProduct.getName() == receiptProduct.getName()) {
//                        pastProduct.setLastBought(receiptDate);
//                        pastProduct.setCount(pastProduct.getCount() + 1);
//                        pastProduct.updateFrequency();
//                        service.updateListDetails(shoppingList);
//                        // would this be updated in the database based on line 238?
//
//                    } else {
//                        listProduct = new ShoppingListProduct(canonicalName, receiptDate);
//                        shoppingList.addShoppingItems(listProduct);
//                        service.updateListDetails(shoppingList);
//                    }
//                }


                // HALA! this is where we are making a list of products in the data base, we don't need it
//                service.updateProductDetails(addProduct);


                service.updateListDetails(shoppingList);
                service.updateUserDetails(user);
            }
        }

        System.out.println("this is the response status code : " + response.getStatusCode());
        System.out.println("this is the name: " + name.asText());
//        System.out.println("this is the date: " + prettyStringCreatedOn);

        for (JsonNode product : products) {
            JsonNode title = product.path("title");
            String prettyStringTitle = title.toPrettyString();
            System.out.println("this is the item: " + prettyStringTitle);
        }

    }
}
