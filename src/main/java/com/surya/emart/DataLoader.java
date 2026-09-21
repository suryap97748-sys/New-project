package com.surya.emart;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class DataLoader implements CommandLineRunner {
 private final ProductRepository repo;
 public DataLoader(ProductRepository repo){this.repo=repo;}
 public void run(String... a){
  if(repo.count()==0){
   add("Laptop","Electronics",45000,10);
   add("Headphones","Electronics",1999,20);
   add("Smart Watch","Electronics",2999,15);
   add("Backpack","Fashion",1299,25);
  }
 }
 void add(String n,String c,double p,int s){
  Product x=new Product(); x.setName(n);x.setCategory(c);x.setPrice(p);x.setStock(s);repo.save(x);
 }
}
