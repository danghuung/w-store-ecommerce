package app.service.wstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.service.wstore.dto.ProductDto;
import app.service.wstore.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get All Product
    @GetMapping("")
    public ResponseEntity<?> getListProduct() {
        return new ResponseEntity<>(productService.getList(), HttpStatus.OK);
    }

    // Get Detail Product
    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailProduct(@PathVariable int id) {
        return new ResponseEntity<>(productService.getDetail(id), HttpStatus.OK);
    }

    // Create Product
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        try {
            return new ResponseEntity<>(productService.create(productDto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Update Product
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable int id, @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.update(id, productDto), HttpStatus.OK);
    }

    // Delete Product
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
