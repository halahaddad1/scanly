package com.scanly.app.CanonicalProducts;
import au.com.bytecode.opencsv.CSVReader;
import com.scanly.app.service.FirebaseService;

import java.io.FileReader;
import java.util.List;

public class ParseFullCSV {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception
    {
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

            FirebaseService service = new FirebaseService();

            for(String[] saveRow : allRows){
                int i = 1;
                while( i < saveRow.length ){
                    String canonicalName = saveRow[0];
                    String seedName = saveRow[i];
                    if (!seedName.equals("")) {
//                        System.out.println(name + " : " + nickname );
                        CanonicalProduct canonicalProduct = new CanonicalProduct(seedName, canonicalName);
                        service.saveCanonicalProductDetails(canonicalProduct);
                        i += 1;
                    } else {
                        i = saveRow.length;
                    }
                }

//            System.out.println(Arrays.toString(row));
        }



    }
}
