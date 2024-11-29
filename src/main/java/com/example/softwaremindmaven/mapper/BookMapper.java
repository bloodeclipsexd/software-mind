package com.example.softwaremindmaven.mapper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BookMapper {
        public static com.example.softwaremindmaven.dto.BookDto toDto(com.example.softwaremindmaven.dao.BookDao bookDao) {
            com.example.softwaremindmaven.dto.BookDto bookDto = new com.example.softwaremindmaven.dto.BookDto();
            bookDto.setAvailable(bookDao.getAvailable());
            bookDto.setTitle(bookDao.getTitle());
            return bookDto;
        }

        public static com.example.softwaremindmaven.dao.BookDao toDao(com.example.softwaremindmaven.dto.BookDto bookDto) {
            com.example.softwaremindmaven.dao.BookDao bookDao = new com.example.softwaremindmaven.dao.BookDao();
            bookDao.setAvailable(bookDto.getAvailable());
            bookDao.setTitle(bookDto.getTitle());
            return bookDao;
        }
}
