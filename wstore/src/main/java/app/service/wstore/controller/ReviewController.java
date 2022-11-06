package app.service.wstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.service.wstore.dto.ReviewDto;
import app.service.wstore.entity.CustomUserDetails;
import app.service.wstore.jwt.CurrentUser;
import app.service.wstore.service.ReviewService;

@RestController
@RequestMapping("/reviews")
@PreAuthorize("isAuthenticated()")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{product_id}")
    public ResponseEntity<?> getListReviewOfProduct(@PathVariable int product_id) {
        return new ResponseEntity<>(reviewService.getList(product_id), HttpStatus.OK);
    }

    @PostMapping("/{order_id}/{product_id}")
    public ResponseEntity<?> createReview(@RequestBody ReviewDto reviewDto, @PathVariable int order_id,
            @PathVariable int product_id, @CurrentUser CustomUserDetails currentUser) {

        return new ResponseEntity<>(reviewService.create(reviewDto, order_id, product_id, currentUser),
                HttpStatus.CREATED);
    }

}
