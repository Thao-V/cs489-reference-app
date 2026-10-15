package com.example.lesson61.dto;

import lombok.Data;

@Data 
public class BookRequestDTO {
  private String title;
  private String ISBN;
  private String author;
}
