package com.scanly.app.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder(toBuilder = true)
@SuperBuilder
public class Product {

    private String name;

}
