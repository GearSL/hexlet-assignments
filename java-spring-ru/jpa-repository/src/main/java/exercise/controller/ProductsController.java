package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping
    public List<Product> getProducts(@RequestParam(name = "min", required = false) Integer min,
                                     @RequestParam(name = "max", required = false) Integer max) {
        if(min != null && max != null) {
            return productRepository.findProductByPriceBetweenOrderByPriceDesc(min, max);
        } else if (min != null) {
            return productRepository.findByPriceGreaterThanEqual(min);
        } else if (max != null) {
            return productRepository.findByPriceLessThanEqual(max);
        } else {
            return productRepository.findAll();
        }
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
