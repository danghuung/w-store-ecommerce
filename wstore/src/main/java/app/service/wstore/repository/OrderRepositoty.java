package app.service.wstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.service.wstore.entity.Order;

@Repository
public interface OrderRepositoty extends JpaRepository<Order, Integer> {
    List<Order> findByCreatedBy(int createdBy);
}
