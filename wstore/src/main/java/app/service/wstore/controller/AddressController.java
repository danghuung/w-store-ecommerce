package app.service.wstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.service.wstore.dto.AddressDto;
import app.service.wstore.jwt.CurrentUser;
import app.service.wstore.service.AddressService;

@RestController
@RequestMapping("/address")
@PreAuthorize("isAuthenticated()")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> getListAddressOfUser(@CurrentUser UserDetails emailCurrentUser) {
        return new ResponseEntity<>(addressService.getList(emailCurrentUser), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> getListDetailAddressOfUser(@PathVariable int id, @CurrentUser UserDetails currentUser) {
        return new ResponseEntity<>(addressService.getDetail(id, currentUser), HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto,
            @CurrentUser UserDetails emailCurrentUser) {

        return new ResponseEntity<>(addressService.create(addressDto, emailCurrentUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> updateAddress(@PathVariable int id, @RequestBody AddressDto addressDto,
            @CurrentUser UserDetails currentUser) {

        return new ResponseEntity<>(addressService.update(id, addressDto, currentUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<?> deleteAddress(@PathVariable int id, @CurrentUser UserDetails currentUser) {

        return new ResponseEntity<>(addressService.delete(id, currentUser), HttpStatus.NO_CONTENT);
    }

}
