package com.andreimedvedev.sdp;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class AbstractDataProcessorTest extends TestCase {

    DataProcessor processor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        processor.clean();
    }

    public final void testById() {
        processor.processOrderedById(getTestConsumer(DataRecord.BY_ID_ASC_COMPARATOR));
    }

    public final void testByName() {
        processor.processOrderedByName(getTestConsumer(DataRecord.BY_NAME_ASC_COMPARATOR));
    }

    private DataRecord previous = null;

    private Consumer<DataRecord> getTestConsumer(Comparator<DataRecord> testComparator) {
        return record -> {
            if (previous != null) {
                var compareResult = Objects.compare(previous, record, testComparator);
                assertTrue("Each prev record expected to be less or equal to current", compareResult <= 0);
            }
            previous = record;
            System.out.println(record);
        };
    }

}
