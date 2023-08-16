package com.SpringWebApplication.SpringWebApplicatoin.Beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// todo list Bean
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// Jpa
// Bean -> database table
@Entity(name = "todo")
public class ListOfTodos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  int id;
    private String username;
    @Size(min = 10, message = "Enter at least 10 letters")// for validation
    private String description;
    private LocalDate  DATE_COLUMN;
    private boolean done;
}
