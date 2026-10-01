package com.codingshuttle.SecurityApp.SecurityApplication.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO{
    private Long id;

    private String title;

    private String description;
}
