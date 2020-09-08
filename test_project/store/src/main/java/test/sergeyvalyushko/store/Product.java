package test.sergeyvalyushko.store;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class Product {
    private String name;
    private Integer price;
    private Date date;
}
