package com.example.lesson61.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.lesson61.dto.BookRequestDTO;
import com.example.lesson61.entity.Book;
import com.example.lesson61.repository.BookRepository;


@Service 
public class BookService {
  private final BookRepository bookRepository;
  private final ModelMapper modelMapper;
  public BookService(BookRepository bookRepository, ModelMapper modelMapper){
    this.bookRepository = bookRepository;
    this.modelMapper = modelMapper;
  }

  public List<Book> getAllBooks(){
    return bookRepository.findAll();
  }

  public Book saveBook(BookRequestDTO request){
    Book book = modelMapper.map(request, Book.class);
    System.out.println(request);
    Book saved = bookRepository.save(book);
    return saved;
  }

  public Book findBookById(Long id) {
    return bookRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Book not found: " + id));
  }
}
