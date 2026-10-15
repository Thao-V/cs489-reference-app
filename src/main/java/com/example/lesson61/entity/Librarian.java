package com.example.lesson61.entity;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@DiscriminatorValue("librarian")
@Getter 
@Setter 
@NoArgsConstructor 
public class Librarian extends User {
  private Double salary;

  @OneToMany (mappedBy = "librarian")
  private List<BookCopy> bookCopies;
}
