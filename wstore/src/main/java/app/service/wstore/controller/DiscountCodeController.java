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

import app.service.wstore.dto.DiscountCodeDto;
import app.service.wstore.service.DiscountCodeService;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/discount-codes")
public class DiscountCodeController {

    @Autowired
    private DiscountCodeService discountCodeService;

    @GetMapping("")
    public ResponseEntity<?> getListDiscountCodeForCustomer() {

        return new ResponseEntity<>(discountCodeService.getListForCustomer(), HttpStatus.OK);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getListDiscountCodeForAdmin() {

        return new ResponseEntity<>(discountCodeService.getListForAdmin(), HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DiscountCodeDto> createDiscountCode(@RequestBody DiscountCodeDto discountCodeDto) {

        return new ResponseEntity<>(discountCodeService.create(discountCodeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateCode(@PathVariable int id, @RequestBody DiscountCodeDto discountCodeDto) {

        return new ResponseEntity<>(discountCodeService.update(id, discountCodeDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteCode(@PathVariable int id) {
        discountCodeService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
