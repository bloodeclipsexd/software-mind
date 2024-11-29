package com.example.softwaremindmaven.controller;

import com.example.softwaremindmaven.dto.BookDto;
import com.example.softwaremindmaven.service.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    @GetMapping("user/{userId}")
    public Set<BookDto> getBorrowedBooks(@PathVariable UUID userId) {
        log.debug("Controller: Getting borrowed books for user with id: {}", userId);
        return bookService.getBorrowedBooks(userId);
    }

    @GetMapping("/borrow/{bookId}/by/{userId}")
    public String borrowBook(@PathVariable UUID bookId, @PathVariable UUID userId) {
        log.debug("Controller: Borrowing book with id: {}", bookId);
        return bookService.borrowBook(bookId, userId);
    }

    @GetMapping("/return/{id}/by/{userId}")
    public String returnBook(@PathVariable UUID id, @PathVariable UUID userId) {
        log.debug("Controller: Returning book with id: {}", id);
        return bookService.returnBook(id, userId);
    }

    @PostMapping
    public String createBook(@RequestBody BookDto book) {
        log.debug("Controller: Creating book: {}", book);
        return bookService.createBook(book);
    }
}
