package app.service.wstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.service.wstore.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Boolean existsByName(String name);
}
