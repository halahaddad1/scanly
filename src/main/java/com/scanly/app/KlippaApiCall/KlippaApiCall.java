package com.scanly.app.KlippaApiCall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

public class KlippaApiCall {


    public void request() throws JsonProcessingException, JsonMappingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("",headers);
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
        HttpEntity<byte[]> entitystream = new HttpEntity<>(file.getInputStream().readAllBytes(),fileheaders);
        body.add("document", entitystream);
        body.add( "type", "file" );


        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body,headers);

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

    public void klippaMultiPartPostRequest(byte[] arr) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpHeaders fileheaders = new HttpHeaders();
        fileheaders.setContentDisposition(ContentDisposition.parse("form-data; name=\"document\"; filename=\"scan.jpg\""));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("X-Auth-Key" , "");
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
//        FileSystemResource file = new FileSystemResource("IMG_3666.jpg");
//        body.add("image", file );
        HttpEntity<byte[]> entitystream = new HttpEntity<>(arr,fileheaders);
        body.add("document", entitystream);
//        body.add( "type", "file" );


        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body,headers);

        String klippaResourceUrl = "https://custom-ocr.klippa.com/api/v1/parseDocument";
        ResponseEntity<String> response = restTemplate.postForEntity(klippaResourceUrl, entity, String.class);

        System.out.println(response.getBody());
//
//
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        JsonNode name = root.path("date");
        JsonNode createdOn = root.path("purchasedate");
//        JsonNode products = root.path("lines").get(0).path("lineitems");
//        JsonNode contents = root.path("contents").path("quotes").get(0);


//        System.out.println("this is the root: " + response.getBody());

        System.out.println("this is the response status code : " + response.getStatusCode());
        System.out.println("this is the name: " + name);
        System.out.println("this is the date: " + createdOn);

//        for (JsonNode product : products) {
//            System.out.println("this is the date: " + product);
//        }
    }
}
