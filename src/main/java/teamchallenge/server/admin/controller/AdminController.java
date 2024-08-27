package teamchallenge.server.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teamchallenge.server.catalog.book.service.BookService;
import teamchallenge.server.catalog.book.dto.CreateBookDto;
import teamchallenge.server.catalog.book.dto.ResponseBookDto;
import teamchallenge.server.cart.entity.CartHeader;
import teamchallenge.server.cart.dto.CartHeaderDto;
import teamchallenge.server.cart.service.CartServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final BookService bookService;
    private final CartServiceImpl cartService;

    @PostMapping("/book")
    public ResponseEntity<ResponseBookDto> createBook(@RequestBody CreateBookDto createBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(createBookDto));
    }

//    @PostMapping("/book/{id}/images")
//    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile images, @PathVariable Long id) {
//        bookService.saveImages(id, images);
//        return ResponseEntity.ok("");
//    }

    @GetMapping("/cart/all")
    public ResponseEntity<List<CartHeaderDto>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCartHeaders());
    }

//    @PostMapping("/cart/create-cart")
//    public ResponseEntity<Long> createCart(@RequestBody CartHeader cart) {
//        return ResponseEntity.ok(cartService.saveCartHeader(cart));
//    }

    @DeleteMapping("/cart/delete-cart/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        cartService.deleteCartHeader(id);
        return ResponseEntity.ok("");
    }
}
