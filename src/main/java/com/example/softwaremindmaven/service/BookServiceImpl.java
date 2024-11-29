package com.example.softwaremindmaven.service;

import com.example.softwaremindmaven.dto.BookDto;
import com.example.softwaremindmaven.mapper.BookMapper;
import com.example.softwaremindmaven.repository.BookRepository;
import com.example.softwaremindmaven.repository.UserRepository;
import com.example.softwaremindmaven.service.interfaces.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    
    private final static String BOOK_NOT_FOUND = "Book not found";
    private final static String USER_NOT_FOUND = "User not found";

    private static void throwBookNotFoundException() {
        log.error(BOOK_NOT_FOUND);
        throw new RuntimeException(BOOK_NOT_FOUND);
    }

    private static void throwUserNotFoundException() {
        log.error(USER_NOT_FOUND);
        throw new RuntimeException(USER_NOT_FOUND);
    }


    @Override
    public String createBook(BookDto dto) {
        bookRepository.save(dto);
        return "Book created successfully!";
    }

    @Override
    public String borrowBook(UUID bookId, UUID userId) {
        bookRepository.findById(bookId).ifPresentOrElse(bookDao -> userRepository.findById(userId)
                .ifPresentOrElse(userDao -> {
                    userDao.getBooks().add(bookDao);
                    userRepository.save(userDao);
                    bookRepository.updateBookAvailability(bookId, false, userDao);
                }, BookServiceImpl::throwUserNotFoundException), BookServiceImpl::throwBookNotFoundException);

        return "Book borrowed successfully";
    }

    @Override
    public String returnBook(UUID bookId, UUID userId) {
        bookRepository.findById(bookId).ifPresentOrElse(bookDao -> userRepository.findById(userId)
                .ifPresentOrElse(userDao -> {
                    userDao.getBooks().remove(bookDao);
                    userRepository.save(userDao);
                    bookRepository.updateBookAvailability(bookId, true, userDao);
                }, BookServiceImpl::throwUserNotFoundException), BookServiceImpl::throwBookNotFoundException);

        return "Book returned successfully";
    }

    @Override
    public Set<BookDto> getBorrowedBooks(UUID userId) {
        return bookRepository.findAllByUsersId(userId)
                .stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toSet());
    }
}
