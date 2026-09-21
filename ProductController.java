package com.surya.emart;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/products") @CrossOrigin
public class ProductController {
 private final ProductRepository repo;
 public ProductController(ProductRepository repo){this.repo=repo;}
 @GetMapping public List<Product> all(@RequestParam(required=false) String search){
  return search==null||search.isBlank()?repo.findAll():repo.findByNameContainingIgnoreCase(search);
 }
 @PostMapping public Product add(@RequestBody Product p){return repo.save(p);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repo.deleteById(id);}
}