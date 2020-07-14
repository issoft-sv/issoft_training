package test.sergeyvalyushko.store;

import lombok.Data;

import java.util.Date;
@Data
public class Product {
    private String name;
    private Integer price;
    private Date date;
    public Product(String name, Integer price, Date date){
        this.name = name;
        this.price = price;
        this.date = date;
    }
}
