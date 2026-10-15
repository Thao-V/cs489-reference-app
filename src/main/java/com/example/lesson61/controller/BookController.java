package com.example.lesson61.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lesson61.dto.BookRequestDTO;
import com.example.lesson61.entity.Book;
import com.example.lesson61.service.BookService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/books")
public class BookController {
  private final BookService bookService;
  public BookController(BookService bookService){
    this.bookService = bookService;
  }
  @GetMapping
  public ResponseEntity<List<Book>> getMethodName() {
      List<Book> books = bookService.getAllBooks();
      return ResponseEntity.status(HttpStatus.OK).body(books);
  }
  @PostMapping()
  public ResponseEntity<Book> postMethodName(@RequestBody BookRequestDTO request) {
      Book saved = bookService.saveBook(request);
      return ResponseEntity.status(HttpStatus.CREATED)
                            .body(saved);
  }
  
}
