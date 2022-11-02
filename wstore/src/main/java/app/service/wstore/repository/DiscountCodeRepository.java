package app.service.wstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.service.wstore.entity.DiscountCode;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Integer> {
    Boolean existsByCode(String code);
}
