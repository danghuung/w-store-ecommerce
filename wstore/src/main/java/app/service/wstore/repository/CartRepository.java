package app.service.wstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.service.wstore.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
