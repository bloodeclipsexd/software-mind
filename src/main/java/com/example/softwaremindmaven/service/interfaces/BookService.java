package com.example.softwaremindmaven.service.interfaces;

import com.example.softwaremindmaven.dto.BookDto;

import java.util.Set;
import java.util.UUID;

public interface BookService {
    String createBook(BookDto bookDto);
    String borrowBook(UUID bookId, UUID userId);
    String returnBook(UUID bookId, UUID userId);
    Set<BookDto> getBorrowedBooks(UUID userId);
}
