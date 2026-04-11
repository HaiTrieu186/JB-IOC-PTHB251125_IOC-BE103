package re.edu.bt16.mapper;

import re.edu.bt16.dto.response.BookResponse;
import re.edu.bt16.entity.Book;

public class BookMapper {
    public static BookResponse EntityToResponse(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .stock(book.getStock())
                .coverUrl(book.getCoverUrl())
                .build();
    }
}
