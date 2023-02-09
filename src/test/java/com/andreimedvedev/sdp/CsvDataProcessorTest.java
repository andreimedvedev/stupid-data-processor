package com.andreimedvedev.sdp;

public class CsvDataProcessorTest extends AbstractDataProcessorTest {

    @Override
    protected void setUp() throws Exception {
        processor = new CsvDataProcessor();
        processor.loadData();
    }



}
