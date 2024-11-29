package com.example.softwaremindmaven.repository;

import com.example.softwaremindmaven.dao.BookDao;
import com.example.softwaremindmaven.dao.UserDao;
import com.example.softwaremindmaven.dto.BookDto;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<BookDao, UUID> {

    void save(@Nonnull BookDto b);

    @Nonnull
    Optional<BookDao> findById(@Nonnull UUID id);

    @Nonnull
    Set<BookDao> findAllByUsersId(@Nonnull UUID id);

    @Modifying
    @Query("UPDATE BookDao b SET b.available = :available, b.users = :userDao WHERE b.id = :id")
    void updateBookAvailability(@Nonnull UUID id, @Nonnull Boolean available, @Nonnull UserDao userDao);
}
