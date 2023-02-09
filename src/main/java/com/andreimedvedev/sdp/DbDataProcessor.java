package com.andreimedvedev.sdp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class DbDataProcessor extends DataProcessor {

    private Connection connection;

    public DbDataProcessor(final Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void loadData() {
        // actually here we do nothing, all sorting things will be done by DBMS
    }

    @Override
    public void processOrderedById(Consumer<DataRecord> action) {
        processSqlResults("SELECT * FROM data_table ORDER BY id", action);
    }

    @Override
    public void processOrderedByName(Consumer<DataRecord> action) {
        processSqlResults("SELECT * FROM data_table ORDER BY name", action);
    }

    private void processSqlResults(String s, Consumer<DataRecord> action) {
        try (var ps = connection.prepareStatement(s); var rs = ps.executeQuery()) {
            while (rs.next()) {
                final var record = new DataRecord(rs.getLong("id"), rs.getString("name"));
                action.accept(record);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clean() {
        super.clean();
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
