package DataBaseProject;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.DriverManager;

public class DBApplication {

    static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;database=LuxuryDB;encrypt=false;user=sa;password=password";
    static final String JDBC_URL2 = "jdbc:sqlserver://localhost:1433;database=Restaurant;encrypt=false;user=sa;password=password";

    public static void main(String[] args) {

        try {
            //createTable();
            //insertRow();
            //getLocations();
            //updateLocations();
            //deleteLocations();
            //getCategory();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getDataSource(){

        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(JDBC_URL2);
        return dataSource;
    }

    private static void createTable() throws Exception{
        String createTable = "create table Locations (id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, city varchar(50))";
        var statement = getDataSource().getConnection().createStatement();
        statement.execute(createTable);

        System.out.println("The query is executed");
    }

    private static void insertRow() throws Exception{
        String insertSql = "insert into Locations (city) values (?)";
        var statement = getDataSource().getConnection().prepareStatement(insertSql);

        String[] cities = {"Sofia", "Plovdiv", "Varna"};
        for (String city : cities) {
            statement.setString(1, city);
            statement.execute(); // Изпълняваме заявката за всяка стойност
        }

        System.out.println("The query is executed");
    }

    private static void getLocations() throws Exception{
        String getAllLocations = "SELECT * FROM Locations";
        var statement = getDataSource().getConnection().createStatement();
        var resultSet = statement.executeQuery(getAllLocations);

        while (resultSet.next()){
            System.out.println("The locations are: " + resultSet.getString("city"));
        }

        System.out.println("The query is executed");
    }

    private static void updateLocations() throws Exception{
        String updateSql = "UPDATE Locations SET city = ? WHERE city = 'Varna'";
        var statement = getDataSource().getConnection().prepareStatement(updateSql);
        statement.setString(1, "Burgas");
        statement.execute();

        System.out.println("The query is executed");
    }

    private static void deleteLocations() throws Exception{
        String deleteSql = "DELETE FROM Locations WHERE city = 'Burgas'";
        var statement = getDataSource().getConnection().createStatement();
        statement.execute(deleteSql);

        System.out.println("The query is executed");
    }

    private static void getCategory() throws Exception{
        String getAllCategories = "SELECT * FROM Category";
        var statement = DriverManager.getConnection(JDBC_URL).createStatement();
        var resultSet = statement.executeQuery(getAllCategories);

        while (resultSet.next()){
            System.out.println("The categories are: " + resultSet.getString("Category_Name"));
        }

        System.out.println("The query is executed");
    }
}
