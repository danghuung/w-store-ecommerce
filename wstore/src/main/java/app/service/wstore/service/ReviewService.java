package app.service.wstore.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.service.wstore.dto.ReviewDto;
import app.service.wstore.entity.CustomUserDetails;
import app.service.wstore.entity.Order;
import app.service.wstore.entity.Product;
import app.service.wstore.entity.Review;
import app.service.wstore.entity.User;
import app.service.wstore.exception.NotFoundException;
import app.service.wstore.exception.ReviewException;
import app.service.wstore.repository.OrderRepositoty;
import app.service.wstore.repository.ProductRepository;
import app.service.wstore.repository.ReviewRepository;
import app.service.wstore.repository.UserRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderRepositoty orderRepositoty;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ReviewDto> getList(int productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);

        ArrayList<ReviewDto> result = new ArrayList<>();

        for (Review review : reviews) {
            result.add(modelMapper.map(review, ReviewDto.class));
        }

        return result;
    }

    public ReviewDto create(ReviewDto reviewDto, int orderId, int productId, CustomUserDetails currentUser) {
        User user = userRepository.getUser(currentUser);

        Order order = orderRepositoty.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order Id Not found!"));

        User createdByOrder = userRepository.findById(order.getCreatedBy());

        if (order.getOrderStatus().equals("completed") && createdByOrder.equals(user)) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product Id not found!"));

            Review review = new Review();
            review.setContent(reviewDto.getContent());
            review.setRating(reviewDto.getRating());
            review.setProduct(product);
            review.setOrder(order);

            return modelMapper.map(reviewRepository.save(review), ReviewDto.class);
        }

        throw new ReviewException("You Can Not Review!");
    }
}
