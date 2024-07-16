package com.teamchallege.cartservice.entity;

import com.teamchallege.common.entities.cart.CartHeader;
import com.teamchallege.common.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CartHeaderRepository extends JpaRepository<CartHeader, Long> {
    Optional<CartHeader> findByUser(User user);
    //Optional<CartHeader> findByUserId(Long userId);
}
