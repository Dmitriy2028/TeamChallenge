package teamchallenge.server.catalog.category.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(long id) {
        super("Category with id " + id + " not found");
    }
    public CategoryNotFoundException(String category) {
        super("Category with name " + category + " not found");
    }
}
