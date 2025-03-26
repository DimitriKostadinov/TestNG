package DataBaseProject;

import com.zaxxer.hikari.HikariDataSource;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.sql.DataSource;
import java.sql.*;

public class DBTestWithSelenium {
    static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;database=LuxuryDB;encrypt=false;user=sa;password=password";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public static DataSource getDataSource(){

        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(JDBC_URL);
        return dataSource;
    }

    @BeforeTest
    public void setUp() throws Exception {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testgetCategory() throws SQLException {
        String getAllCategories = "SELECT * FROM Category";
        resultSet = statement.executeQuery(getAllCategories);
        boolean firstCat = false;
        boolean secondCat = false;

        while (resultSet.next()) {
            String category = resultSet.getString("Category_Name");
            System.out.println("The categories are: " + category);
            if (category.equals("Камъни")) {
                firstCat = true;
            } else if (category.equals("Благороден метал")) {
                secondCat = true;
            }
        }
        Assert.assertTrue(firstCat && secondCat, "Wrong number of categories!");
    }

    @AfterTest
    public void tearDown() throws Exception{
        // Close DB connection
        try {
            if (resultSet != null)
                resultSet.close();

            if (statement != null)
                statement.close();

            if (connection != null)
                connection.close();

            System.out.println("Database connection closed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
