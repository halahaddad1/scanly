package com.scanly.app.CanonicalProducts;
import au.com.bytecode.opencsv.CSVReader;
import com.scanly.app.service.FirebaseService;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ParseFullCSV {

//
//    @SuppressWarnings("resource")
//    public static void main(String[] args) throws Exception
//    {
//        //Build reader instance
//        CSVReader reader = new CSVReader(new FileReader("src/main/resources/ScanlyCanonicalProducstData.csv"), ',', '"', 1);
//
//        //Read all rows at once
//        List<String[]> allRows = reader.readAll();
//
//        //Read CSV line by line and use the string array as you want
//        for(String[] row : allRows) {
//            int i = 1;
//            while (i < row.length) {
//                String name = row[0];
//                String nickname = row[i];
//                if (!nickname.equals("")) {
//                    System.out.println(name + " : " + nickname);
//                    i += 1;
//                } else {
//                    i = row.length;
//                }
//            }
//        }
//    }

    //@Autowired
    //FirebaseService firebaseService;

    @SuppressWarnings("resource")
    public void parseCSV(FirebaseService firebaseService) throws IOException, ExecutionException, InterruptedException {
        //Build reader instance
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/ScanlyCanonicalProducstData.csv"), ',', '"', 1);

        //Read all rows at once
        List<String[]> allRows = reader.readAll();

        //Read CSV line by line and use the string array as you want
//        for(String[] row : allRows){
//            int i = 1;
//            while( i < row.length ){
//                String name = row[0];
//                String nickname = row[i];
//                if (!nickname.equals("")) {
//                    System.out.println(name + " : " + nickname );
//                    i += 1;
//                } else {
//                    i = row.length;
//                }
//            }

//            FirebaseService service = new FirebaseService();


            for(String[] saveRow : allRows){
                int i = 1;
                while( i < saveRow.length ){
                    String canonicalName = saveRow[0];
                    if (canonicalName.length() == 0) {
                        break;
                    }
                    String seedName = saveRow[i];
                    if (seedName.contains("/")) {
                        seedName = seedName.replace("/", "");
                    }
                    if (!seedName.equals("")) {
//                        System.out.println(name + " : " + nickname );
                        CanonicalProduct canonicalProduct = new CanonicalProduct(seedName, canonicalName);
                        System.out.println(canonicalProduct.getCanonicalName());
                        System.out.println(canonicalProduct.getSeedName());
                        firebaseService.saveCanonicalProductDetails(canonicalProduct);
                        i += 1;
                    } else {
                        break;
                    }
                }

//            System.out.println(Arrays.toString(row));
        }



    }
}
