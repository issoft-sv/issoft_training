package test.sergeyvalyushko.store;

import com.github.javafaker.Faker;
import test.sergeyvalyushko.store.category.Beer;
import test.sergeyvalyushko.store.category.Book;
import test.sergeyvalyushko.store.category.Food;
import test.sergeyvalyushko.store.helpers.RandomStorePopulator;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBhandler {

    private String username = "sa";
    private String password = "sa";
    private String url = "jdbc:h2:~/test";
    private String driver = "org.h2.Driver";
    private String sql = "";
    private Connection conn;
    private Statement st;

    private void createTables() {
        sql =  "CREATE TABLE CATEGORIES " + "(name VARCHAR(255) not NULL)";
        execUpdate(sql);
        sql =  "CREATE TABLE PRODUCTS " +
                "(id INTEGER AUTO_INCREMENT, " +
                " name VARCHAR(255) not NULL, " +
                " category_name VARCHAR(255) not NULL, " +
                " price INTEGER not NULL, " +
                " PRIMARY KEY ( id ), " +
                " FOREIGN KEY (category_name) REFERENCES CATEGORIES (name))";
        execUpdate(sql);
        closeConnection(conn, st);
    }
    private void fillTables(){
        sql = "INSERT INTO CATEGORIES " + "VALUES ('Book'),('Beer'),('Food')";
        execUpdate(sql);
        createDbData("Book");
        createDbData("Beer");
        createDbData("Food");
        closeConnection(conn, st);
    }
    public List<Category> createCatalog(){
        List<Category> categories = new ArrayList<>();
        createTables();
        fillTables();
        sql = "SELECT * FROM CATEGORIES";
        ResultSet rs = execQuery(sql);
        try {
            while (rs.next()) {
                String categoryName = rs.getString("name");
                Category category = null;
                switch (categoryName) {
                    case ("Book"):
                        category = new Book();
                        break;
                    case ("Beer"):
                        category = new Beer();
                        break;
                    case ("Food"):
                        category = new Food();
                        break;
                }
                fillProductList(category);
                categories.add(category);
            }
        } catch (Exception e){e.printStackTrace();}
        closeConnection(conn, st);
        return categories;
    }
    private void fillProductList(Category category) throws SQLException {
        sql = String.format("SELECT * FROM PRODUCTS WHERE category_name = '%s' ", category.getName());
        ResultSet rs = execQuery(sql);
        while (rs.next()) {
            category.getProductList().add(new Product(rs.getString("name"), rs.getInt("price"), new Date()));
        }
        closeConnection(conn, st);
    }
    private void createDbData (String categoryName){
        RandomStorePopulator rsp = new RandomStorePopulator();
        for (int i = 0; i < 5; i++) {
            sql = String.format("INSERT INTO PRODUCTS (name, category_name, price) " + "VALUES ('%s', '%s', %d)", rsp.createProductName(categoryName), categoryName, (int) ((Math.random() * 10) + 1));
            execUpdate(sql);
        }
        closeConnection(conn, st);
    }
    private ResultSet execQuery (String sql){
        try {
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
    private void execUpdate (String sql){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {e.printStackTrace();}
    }
    private void closeConnection (Connection conn, Statement st){
        try {
            st.close();
            conn.close();
        } catch (Exception e){e.printStackTrace();}
    }
}
