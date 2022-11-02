package app.service.wstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import app.service.wstore.entity.User;
import app.service.wstore.exception.NotFoundException;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findById(int id);

    Boolean existsByEmail(String email);

    default User getUser(UserDetails currentUser) {
        return getUserByEmail(currentUser.getUsername());
    }

    default User getUserByEmail(String email) {
        if (!existsByEmail(email)) {
            throw new NotFoundException("Not Found User");
        }
        return findByEmail(email);
    }
}
