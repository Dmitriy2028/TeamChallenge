package com.teamchallege.userservice.user.service;


import com.teamchallege.common.entities.cart.CartHeader;
import com.teamchallege.userservice.role.service.RoleService;
import com.teamchallege.common.entities.user.User;
import com.teamchallege.userservice.user.dto.AddUserToCartDto;
import com.teamchallege.userservice.user.dto.CreateUserDto;
import com.teamchallege.userservice.user.entity.UserRepository;
import com.teamchallege.userservice.user.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final RestTemplate restTemplate;

    private final PasswordEncoder passwordEncoder;


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }


    @Override
    //@Transactional
    public User createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user.setRoles(List.of(roleService.findByName("ROLE_PERSONAL")));
        user = userRepository.save(user);
        if (createUserDto.getCartHeaderId() != null) {
            AddUserToCartDto addUserToCartDto = new AddUserToCartDto();
            addUserToCartDto.setUser(user);
            addUserToCartDto.setCartHeaderId(createUserDto.getCartHeaderId());
            user.setCartHeader(restTemplate.postForObject("http://cart-service/internal/cart/addUserToCart", addUserToCartDto, CartHeader.class));
        } else {
            user.setCartHeader(restTemplate.postForObject("http://cart-service/internal/cart/createCart", user, CartHeader.class));
        }

        return findByEmail(user.getEmail());
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
