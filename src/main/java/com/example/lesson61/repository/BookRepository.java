package com.example.lesson61.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lesson61.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
