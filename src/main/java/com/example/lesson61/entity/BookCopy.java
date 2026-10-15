package com.example.lesson61.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity 
public class BookCopy {
  @Id 
  @GeneratedValue 
  private Long id;

  private LocalDateTime borrowDate;
  private LocalDateTime returnDate;

  @NotNull 
  private boolean available;

  @ManyToOne 
  @JoinColumn (name = "book_id")
  private Book book;

  @ManyToOne 
  @JoinColumn (name = "librarian_id")
  private Librarian librarian;
}
