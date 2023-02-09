package com.andreimedvedev.sdp;


import java.io.*;
import java.util.Comparator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

public class CsvDataProcessor extends DataProcessor {

    private SortedSet<DataRecord> byId = new TreeSet<>(DataRecord.BY_ID_ASC_COMPARATOR);
    private SortedSet<DataRecord> byName = new TreeSet<>(DataRecord.BY_NAME_ASC_COMPARATOR);

    @Override
    public void loadData() {
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream("myFile0.csv")))) {
            String line = reader.readLine(); // skipping first line with field names
            do {
                line = reader.readLine();
                loadLine(line);
            } while (line != null);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadLine(String line) {
        if (line != null) {
            var fields = line.split(",");
            if (fields.length == 2) {
                var record = new DataRecord(Long.parseLong(fields[0]), fields[1]);
                byId.add(record);
                byName.add(record);
            } else {
                throw new RuntimeException("Invalid data in source");
            }
        }
    }

    @Override
    public void processOrderedById(Consumer<DataRecord> action) {
        byId.forEach(action);
    }

    @Override
    public void processOrderedByName(Consumer<DataRecord> action) {
        byName.forEach(action);
    }

}
