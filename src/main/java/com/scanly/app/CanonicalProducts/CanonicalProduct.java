//package com.scanly.app.CanonicalProducts;
//
//import com.opencsv.bean.CsvBindByName;
//
//public class CanonicalProduct {
//
//    @CsvBindByName(column = "product")
//    private String name;
//    @CsvBindByName
//    private String nickname;
//
//    public CanonicalProduct(String name, String nickname) {
//        this.name = name;
//        this.nickname = nickname;
//    }
//}

package com.scanly.app.CanonicalProducts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CanonicalProduct {

    private String seedName;
    private String canonicalName;

}
