package com.example.softwaremindmaven.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {
    private UUID id;
    private Boolean available;
    private String title;
    private UserDto user;
}
