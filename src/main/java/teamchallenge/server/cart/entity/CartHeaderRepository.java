package teamchallenge.server.cart.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamchallenge.server.user.entity.User;

import java.util.Optional;

@Repository
public interface CartHeaderRepository extends JpaRepository<CartHeader, Long> {
    Optional<CartHeader> findByUser(User user);
}
