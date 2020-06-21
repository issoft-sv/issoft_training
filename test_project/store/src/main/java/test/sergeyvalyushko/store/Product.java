package test.sergeyvalyushko.store;

import java.util.Date;

public class Product {
    private String name;
    private Double price;
    private Date made_date;
    public Product(String name, Double price, Date made_date){
        this.name = name;
        this.price = price;
        this.made_date = made_date;
    }
    public String getName (){ return this.name; }
    public Double getPrice (){
        return this.price;
    }
    public Date getDate (){ return this.made_date; }

}
