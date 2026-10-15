package com.example.lesson61.entity;

import java.io.Serial;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@Getter 
@Setter 
@NoArgsConstructor
@ToString 
public class User {
  @Id 
  @GeneratedValue 
  private Long id;

  private String name;
  @NotEmpty 
  @Size (max = 100)
  private String email;
  @NotEmpty 
  @Size (max = 16)
  private String password;
}
