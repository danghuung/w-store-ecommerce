package app.service.wstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.service.wstore.dto.CartDto;
import app.service.wstore.entity.Cart;
import app.service.wstore.service.CartService;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("")
    public ResponseEntity<Cart> createCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartService.create(cartDto), HttpStatus.CREATED);
    }
}
