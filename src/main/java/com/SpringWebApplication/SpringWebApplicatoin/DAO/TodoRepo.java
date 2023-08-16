package com.SpringWebApplication.SpringWebApplicatoin.DAO;

import com.SpringWebApplication.SpringWebApplicatoin.Beans.ListOfTodos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepo extends JpaRepository<ListOfTodos, Integer> {
    public List<ListOfTodos> findByUsername(String username);
}
