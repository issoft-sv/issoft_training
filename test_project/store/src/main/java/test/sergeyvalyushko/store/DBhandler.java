package test.sergeyvalyushko.store;

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

    private void createTables() {
        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection (url, username,password);
            Statement st = conn.createStatement();
            sql =  "CREATE TABLE CATEGORIES " +
                    "(name VARCHAR(255) not NULL PRIMARY KEY)";
            st.executeUpdate(sql);
            String sql =  "CREATE TABLE PRODUCTS " +
                "(id INTEGER AUTO_INCREMENT, " +
                " name VARCHAR(255) not NULL, " +
                " category_name VARCHAR(255) not NULL, " +
                " price INTEGER not NULL, " +
                " PRIMARY KEY ( id ), " +
                " FOREIGN KEY (category_name) REFERENCES CATEGORIES (name))";
            st.executeUpdate(sql);
            st.close();
            conn.close();
        } catch (Exception e) {e.printStackTrace();}
    }
    private void fillTables(){
        this.createTables();
        sql = "INSERT INTO CATEGORIES " + "VALUES ('Book'),('Beer'),('Food')";
        try {
            Connection conn = DriverManager.getConnection (url, username,password);
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            RandomStorePopulator pop = new RandomStorePopulator();
            pop.createDbData("Book", conn);
            pop.createDbData("Beer", conn);
            pop.createDbData("Food", conn);
            st.close();
            conn.close();
        } catch (Exception e){e.printStackTrace();}
    }
    public List<Category> createCatalog(){
        List<Category> categories = new ArrayList<>();
        this.fillTables();
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            sql = "SELECT * FROM CATEGORIES";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String categoryName = rs.getString("name");
                switch (categoryName) {
                    case ("Book"):
                        Book book = new Book();
                        this.fillProductList(book);
                        categories.add(book);
                        break;
                    case ("Beer"):
                        Beer beer = new Beer();
                        this.fillProductList(beer);
                        categories.add(beer);
                        break;
                    case ("Food"):
                        Food food = new Food();
                        this.fillProductList(food);
                        categories.add(food);
                        break;
                }
            }
            st.close();
            conn.close();
        } catch (Exception e) {e.printStackTrace();}
        return categories;
    }
    private void fillProductList(Category category){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            sql = String.format("SELECT * FROM PRODUCTS WHERE category_name = '%s' ", category.getName());
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                category.getProductList().add(new Product(rs.getString("name"), rs.getInt("price"), new Date()));
            }
            st.close();
            conn.close();
        } catch (Exception e) {e.printStackTrace();}
    }
}
