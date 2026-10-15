package com.example.lesson61.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
public class Book {
  @Id 
  @GeneratedValue 
  private Long id;

  private String title;
  private String ISBN;
  private String author;

  @OneToMany(mappedBy = "book")
  private List<BookCopy> bookCopies;
}
