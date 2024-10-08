package teamchallenge.server.catalog.book.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListResponseBookDto {
    private Long id;
    private String title;
    private int year;
    private double price;
    private int totalQuantity;
    private List<String> languages;
    private boolean isExpected;
    private List<String> authors;
    private String imageUrl;
    private Integer discount;
    private double discountPrice;
}
