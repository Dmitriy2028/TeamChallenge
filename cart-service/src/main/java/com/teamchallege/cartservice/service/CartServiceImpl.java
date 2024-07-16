package com.teamchallege.cartservice.service;

import com.teamchallege.cartservice.mapper.CartHeaderMapper;
import com.teamchallege.common.entities.catalog.Book;
import com.teamchallege.common.entities.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.teamchallege.cartservice.dto.CartHeaderDto;
import com.teamchallege.common.entities.cart.CartHeader;
import com.teamchallege.cartservice.entity.CartHeaderRepository;
import com.teamchallege.common.entities.cart.CartItem;
import com.teamchallege.cartservice.entity.CartItemRepository;
import com.teamchallege.cartservice.exception.CartHeaderForUserNotFoundException;
import com.teamchallege.cartservice.exception.CartHeaderNotFoundException;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartHeaderRepository cartHeaderRepository;

    private final CartItemRepository cartItemRepository;

    private final CartHeaderMapper cartHeaderMapper;

    private final RestTemplate restTemplate;

    @Override
    public List<CartHeaderDto> getAllCartHeaders() {

        return cartHeaderRepository.findAll().stream()
                .map(cartHeaderMapper::toDto)
                .toList();
    }

    @Override
    public CartHeaderDto getCartHeaderById(Long id) {
        CartHeader cartHeader = cartHeaderRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cartHeaderMapper.toDto(cartHeader);
    }

    @Override
    public CartHeader getCartHeaderEntityById(Long id) {
        return cartHeaderRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public Long saveCartHeader(CartHeader cartHeader) {
        return cartHeaderRepository.save(cartHeader).getId();
    }

    @Override
    public void deleteCartHeader(Long id) {
        cartHeaderRepository.deleteById(id);
    }


    @Override
    public CartHeader createCart(User user) {
        CartHeader cartHeader = new CartHeader();
        cartHeader.setUser(user);
        return cartHeaderRepository.save(cartHeader);
    }

    @Override
    public Long createCart() {
        CartHeader cartHeader = new CartHeader();
        return cartHeaderRepository.save(cartHeader).getId();
    }

    @Override
    public CartHeader addUserToCart(User user, Long cartHeaderId) {
        CartHeader cartHeader = cartHeaderRepository.findById(cartHeaderId).orElseThrow(() -> new CartHeaderNotFoundException(cartHeaderId));
        cartHeader.setUser(user);
        return cartHeaderRepository.save(cartHeader);
    }

    @Override
    public CartHeaderDto getCartByUser(String email) {
        CartHeader cartHeader = restTemplate.postForObject("http://user-service/internal/user/getUserByEmail", email, User.class).getCartHeader();
        return cartHeaderMapper.toDto(cartHeader);
    }

    @Override
    @Transactional
    public CartHeaderDto addBook(Long bookId, Long quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();

        User user = restTemplate.postForObject("http://user-service/internal/user/getUserByEmail", email, User.class);
        CartHeader cartHeader = cartHeaderRepository.findByUser(user).orElseThrow(() -> new CartHeaderForUserNotFoundException(user));
        Book book = restTemplate.postForObject("http://catalog-service/internal/catalog/book/getBookById", bookId, Book.class);

        CartItem cartItem = cartItemRepository.findByCartHeaderAndBook(cartHeader, book).orElse(new CartItem());
        cartItem.setCartHeader(cartHeader);
        cartItem.setBook(book);

        cartHeader.setTotalPrice(cartHeader.getTotalPrice() - cartItem.getPrice());

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItem.setPrice(cartItem.getQuantity() * book.getPrice());

        cartHeader.setTotalPrice(cartHeader.getTotalPrice() + cartItem.getPrice());

        cartItemRepository.save(cartItem);
        cartHeaderRepository.save(cartHeader);

        return cartHeaderMapper.toDto(cartHeader);
    }

    public CartHeaderDto addBook(Long bookId, Long quantity, Long cartHeaderId) {
        CartHeader cartHeader = cartHeaderRepository.findById(cartHeaderId).orElseThrow(() -> new CartHeaderNotFoundException(cartHeaderId));
        Book book = restTemplate.postForObject("http://catalog-service/internal/catalog/book/getBookById", bookId, Book.class);

        CartItem cartItem = cartItemRepository.findByCartHeaderAndBook(cartHeader, book).orElse(new CartItem());
        cartItem.setCartHeader(cartHeader);
        cartItem.setBook(book);

        cartHeader.setTotalPrice(cartHeader.getTotalPrice() - cartItem.getPrice());

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItem.setPrice(cartItem.getQuantity() * book.getPrice());

        cartHeader.setTotalPrice(cartHeader.getTotalPrice() + cartItem.getPrice());

        cartItemRepository.save(cartItem);
        cartHeaderRepository.save(cartHeader);

        return cartHeaderMapper.toDto(cartHeader);
    }

    @Override
    @Transactional
    public CartHeaderDto removeBook(Long bookId, Long quantity) {
        try {
            logger.info("Starting removeBook method");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getPrincipal().toString();

            User user = restTemplate.postForObject("http://user-service/internal/user/getUserByEmail", email, User.class);
            CartHeader cartHeader = cartHeaderRepository.findByUser(user).orElseThrow(() -> new CartHeaderForUserNotFoundException(user));
            Book book = restTemplate.postForObject("http://catalog-service/internal/catalog/book/getBookById", bookId, Book.class);

            CartItem cartItem = cartItemRepository.findByCartHeaderAndBook(cartHeader, book).orElseThrow(() -> new RuntimeException("Book in cart not found"));

            Long newQuantity = cartItem.getQuantity() - quantity;

            cartHeader.setTotalPrice(cartHeader.getTotalPrice() - cartItem.getPrice());

            if (newQuantity > 0) {
                logger.info("Updating cart item: {}", cartItem.getId());
                cartItem.setQuantity(newQuantity);
                cartItem.setPrice(cartItem.getQuantity() * book.getPrice());
                cartHeader.setTotalPrice(cartHeader.getTotalPrice() + cartItem.getPrice());
                cartItemRepository.save(cartItem);
                logger.info("Updated cart item: {}", cartItem.getId());
            } else {
                logger.info(cartHeader.toString());
                logger.info("Deleting cart item: {}", cartItem.getId());
                cartItemRepository.delete(cartItem);
                cartHeader.getCartItems().remove(cartItem);
                logger.info("Deleted cart item: {}", cartItem.getId());
                logger.info(cartHeader.toString());
            }

            cartHeader = cartHeaderRepository.save(cartHeader);
            logger.info("Completed removeBook method successfully");
            return cartHeaderMapper.toDto(cartHeader);

        } catch (Exception e) {
            logger.error("Error while removing book from cart", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public CartHeaderDto removeBook(Long bookId, Long quantity, Long cartHeaderId) {
        try {
            logger.info("Starting removeBook method");
            CartHeader cartHeader = cartHeaderRepository.findById(cartHeaderId).orElseThrow(() -> new CartHeaderNotFoundException(cartHeaderId));
            Book book = restTemplate.postForObject("http://catalog-service/internal/catalog/book/getBookById", bookId, Book.class);

            CartItem cartItem = cartItemRepository.findByCartHeaderAndBook(cartHeader, book).orElseThrow(() -> new RuntimeException("Book in cart not found"));

            Long newQuantity = cartItem.getQuantity() - quantity;

            cartHeader.setTotalPrice(cartHeader.getTotalPrice() - cartItem.getPrice());

            if (newQuantity > 0) {
                logger.info("Updating cart item: {}", cartItem.getId());
                cartItem.setQuantity(newQuantity);
                cartItem.setPrice(cartItem.getQuantity() * book.getPrice());
                cartHeader.setTotalPrice(cartHeader.getTotalPrice() + cartItem.getPrice());
                cartItemRepository.save(cartItem);
                logger.info("Updated cart item: {}", cartItem.getId());
            } else {
                logger.info(cartHeader.toString());
                logger.info("Deleting cart item: {}", cartItem.getId());
                cartItemRepository.delete(cartItem);
                cartHeader.getCartItems().remove(cartItem);
                logger.info("Deleted cart item: {}", cartItem.getId());
                logger.info(cartHeader.toString());
            }

            cartHeader = cartHeaderRepository.save(cartHeader);
            logger.info("Completed removeBook method successfully");
            return cartHeaderMapper.toDto(cartHeader);

        } catch (Exception e) {
            logger.error("Error while removing book from cart", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public CartHeaderDto removeAllBooks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();

        User user = restTemplate.postForObject("http://user-service/internal/user/getUserByEmail", email, User.class);

        CartHeader cartHeader = cartHeaderRepository.findByUser(user).orElseThrow(() -> new CartHeaderForUserNotFoundException(user));

        cartItemRepository.deleteAll(cartHeader.getCartItems());
        cartHeader.getCartItems().clear();
        cartHeader.setTotalPrice((double) 0);

        cartHeaderRepository.save(cartHeader);

        return cartHeaderMapper.toDto(cartHeader);
    }

    @Override
    @Transactional
    public CartHeaderDto removeAllBooks(Long cartHeaderId) {
        CartHeader cartHeader = cartHeaderRepository.findById(cartHeaderId).orElseThrow(() -> new CartHeaderNotFoundException(cartHeaderId));

        cartItemRepository.deleteAll(cartHeader.getCartItems());
        cartHeader.getCartItems().clear();
        cartHeader.setTotalPrice((double) 0);

        cartHeaderRepository.save(cartHeader);

        return cartHeaderMapper.toDto(cartHeader);
    }

}
