package com.andreimedvedev.sdp;

import java.util.Comparator;

public record DataRecord(long id, String name) {

    public static final Comparator<DataRecord> BY_ID_ASC_COMPARATOR = Comparator.comparingLong(DataRecord::id);
    public static final Comparator<DataRecord> BY_NAME_ASC_COMPARATOR = Comparator.comparing(DataRecord::name);


}
