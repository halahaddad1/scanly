package com.scanly.app.CanonicalProducts;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import com.scanly.app.service.FirebaseService;

public class ParseFullCSV {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception
    {
        //Build reader instance
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/ScanlyCanonicalProducstData.csv"), ',', '"', 1);

        //Read all rows at once
        List<String[]> allRows = reader.readAll();

        //Read CSV line by line and use the string array as you want
        for(String[] row : allRows){
            int i = 1;
            while( i < row.length ){
                String name = row[0];
                String nickname = row[i];
                if (!nickname.equals("")) {
                    System.out.println(name + " : " + nickname );
                    i += 1;
                } else {
                    i = row.length;
                }
            }

            FirebaseService service = new FirebaseService();

            for(String[] saveRow : allRows){
                int i = 1;
                while( i < saveRow.length ){
                    String name = saveRow[0];
                    String nickname = saveRow[i];
                    if (!nickname.equals("")) {
                        System.out.println(name + " : " + nickname );
                        i += 1;
                    } else {
                        i = saveRow.length;
                    }
                }
//            String first = row[0];
//            System.out.println(first + : );
//            System.out.println(Arrays.toString(row));
        }



    }
}
