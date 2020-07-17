package com.scanly.app;

import com.scanly.app.CanonicalProducts.ParseFullCSV;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {

        public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
            ParseFullCSV parse = new ParseFullCSV();
            parse.parseCSV();
        }
    }

