package com.example.lesson61.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.lesson61.entity.Book;
import com.example.lesson61.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private BookService bookService;

  @Test
  void shouldFindBookById() {

    // Arrange
    Book book = new Book();
    book.setId(1L);
    book.setTitle("Clean Code");

    when(bookRepository.findById(1L))
        .thenReturn(Optional.of(book));

    // Act
    Book result = bookService.findBookById(1L);

    // Assert
    assertEquals("Clean Code", result.getTitle());
    verify(bookRepository).findById(1L);
  }

  @Test
  void shouldThrowExceptionWhenBookNotFound() {
    // Arrange
    when(bookRepository.findById(99L))
        .thenReturn(Optional.empty());
    // Act
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> bookService.findBookById(99L));
  // Assert
    assertEquals(
        "Book not found: 99",
        exception.getMessage());
    verify(bookRepository).findById(99L);
  }
}
