package app.service.wstore.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.service.wstore.dto.ProductDto;
import app.service.wstore.entity.Category;
import app.service.wstore.entity.Product;
import app.service.wstore.exception.NotFoundException;
import app.service.wstore.repository.CategoryRepository;
import app.service.wstore.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    // List All Product
    public List<ProductDto> getList() {
        List<Product> products = productRepository.findAll();
        ArrayList<ProductDto> result = new ArrayList<ProductDto>();

        for (Product product : products) {
            if (product.getIsActive() == true) {

                result.add(modelMapper.map(product, ProductDto.class));
            }
        }
        return result;
    }

    // Get Detail Product
    public ProductDto getDetail(int id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Id not exist!"));

        return modelMapper.map(product, ProductDto.class);

    }

    // Create Product
    public ProductDto create(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
        return modelMapper.map(product, ProductDto.class);
    }

    // Update Product
    public ProductDto update(int id, ProductDto productDto) {

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category Id not exist!"));

        productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Id not exist!"));

        productDto.setId(id);
        Product newProduct = modelMapper.map(productDto, Product.class);
        newProduct.setCategory(category);

        return modelMapper.map(productRepository.save(newProduct), ProductDto.class);
    }

    // Delete Product
    public void delete(int id) {
        productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Id not exist!"));

        productRepository.deleteById(id);
    }

}
