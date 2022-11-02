package app.service.wstore.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.service.wstore.entity.Order;
import app.service.wstore.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    Set<OrderDetail> findByOrder(Order order);
}
