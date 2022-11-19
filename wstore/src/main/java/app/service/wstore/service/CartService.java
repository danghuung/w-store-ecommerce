package app.service.wstore.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.service.wstore.dto.CartDto;
import app.service.wstore.entity.Cart;
import app.service.wstore.entity.Product;
import app.service.wstore.exception.NotFoundException;
import app.service.wstore.repository.CartRepository;
import app.service.wstore.repository.ProductRepository;
import app.service.wstore.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Cart create(CartDto cartDto) {
        Product product = productRepository.findById(cartDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found!"));

        Cart newCart = new Cart();

        newCart.setProducts(product);
        newCart.setQuantity(cartDto.getQuantity());

        return cartRepository.save(newCart);
    }

}
