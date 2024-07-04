package teamchallenge.server.services;

import teamchallenge.server.dto.CartHeaderDto;
import teamchallenge.server.entities.CartHeader;
import teamchallenge.server.entities.User;

import java.util.List;

public interface CartService {
    List<CartHeaderDto> getAllCartHeaders();

    CartHeaderDto getCartHeaderById(Long id);

    CartHeader getCartHeaderEntityById(Long id);

    Long saveCartHeader(CartHeader cartHeader);

    void deleteCartHeader(Long id);


    CartHeader createCart(User user);

    Long createCart();

    CartHeader addUserToCart(User user, Long cartHeaderId);

    CartHeaderDto getCartByUser(String email);

    CartHeaderDto addBook(Long bookId, Long quantity);

    CartHeaderDto addBook(Long bookId, Long quantity, Long cartHeaderId);

    CartHeaderDto removeBook(Long bookId, Long quantity);

    CartHeaderDto removeBook(Long bookId, Long quantity, Long cartHeaderId);

    CartHeaderDto removeAllBooks();

    CartHeaderDto removeAllBooks(Long cartHeaderId);

}
