package com.andreimedvedev.sdp;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbDataProcessorTest extends AbstractDataProcessorTest {

    Connection conn;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String url="jdbc:hsqldb:mem:db/mainDb";
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        conn = DriverManager.getConnection(url, "SA", "");
        processor = new DbDataProcessor(conn);
        initDbStructure();
        fillRandomData();
    }

    private void fillRandomData() {
        try (var ps = conn.prepareStatement("""
            INSERT INTO data_table (id, name) VALUES (?,?)
          """)) {
            for (int i = 30; i > 0; i--) {
                ps.setInt(1, i);
                ps.setString(2, "String " + (char) (90 - i));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void tearDown() throws Exception {
        try (var ps = conn.prepareStatement("""
            DROP TABLE data_table 
          """)) {
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        processor.clean();
    }

    private void initDbStructure() {
        try (var ps = conn.prepareStatement("""
            CREATE TABLE data_table (
                id NUMERIC(10) NOT NULL PRIMARY KEY,
                name VARCHAR(256))
          """)) {
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
