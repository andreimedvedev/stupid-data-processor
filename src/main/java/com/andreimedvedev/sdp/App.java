package com.andreimedvedev.sdp;


import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args) {
        CsvDataProcessor dp = new CsvDataProcessor();
        dp.loadData();
        dp.processOrderedByName(System.out::println);
    }

}
