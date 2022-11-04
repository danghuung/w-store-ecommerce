package app.service.wstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.service.wstore.dto.OrderDto;
import app.service.wstore.entity.CustomUserDetails;
import app.service.wstore.jwt.CurrentUser;
import app.service.wstore.service.OrderService;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public ResponseEntity<?> getListOrder(@CurrentUser CustomUserDetails currentUser) {
        if (currentUser.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return new ResponseEntity<>(orderService.getListOrderForAdmin(), HttpStatus.OK);
        }
        return new ResponseEntity<>(orderService.getListOrderForCustomer(currentUser), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> getOrder(@PathVariable int id, @CurrentUser CustomUserDetails currentUser) {
        return new ResponseEntity<>(orderService.getDettailOrderForCustomer(id, currentUser), HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        try {

            return new ResponseEntity<>(orderService.create(orderDto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> updateStatusOrder(@PathVariable int id, @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.updateStatusOrder(id, orderDto), HttpStatus.OK);
    }
}
