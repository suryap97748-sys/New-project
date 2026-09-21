package com.surya.emart;
import jakarta.persistence.*;
@Entity
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String name, category, image; private double price; private int stock;
 public Long getId(){return id;} public String getName(){return name;} public String getCategory(){return category;}
 public String getImage(){return image;} public double getPrice(){return price;} public int getStock(){return stock;}
 public void setName(String x){name=x;} public void setCategory(String x){category=x;} public void setImage(String x){image=x;}
 public void setPrice(double x){price=x;} public void setStock(int x){stock=x;}
}