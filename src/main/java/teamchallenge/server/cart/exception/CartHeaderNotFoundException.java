package teamchallenge.server.cart.exception;

public class CartHeaderNotFoundException extends RuntimeException {
    public CartHeaderNotFoundException(Long cartHeaderId) {
        super("Cart with id " + cartHeaderId.toString() + " not found");
    }
}
