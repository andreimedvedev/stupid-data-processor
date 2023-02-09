package com.andreimedvedev.sdp;

import java.util.function.Consumer;

public abstract class DataProcessor {

    public abstract void loadData();

    public abstract void processOrderedById(Consumer<DataRecord> record);

    public abstract void processOrderedByName(Consumer<DataRecord> record);

    public void clean() {

    }

}
