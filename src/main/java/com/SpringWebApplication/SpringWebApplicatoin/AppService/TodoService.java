package com.SpringWebApplication.SpringWebApplicatoin.AppService;

import com.SpringWebApplication.SpringWebApplicatoin.Beans.ListOfTodos;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static int todocount=0;
    private static List<ListOfTodos> todo = new ArrayList<>();

    static{
        todo.add(new ListOfTodos(++todocount, "masoom", "AWS course Certification", LocalDate.now().plusYears(2), true));
        todo.add(new ListOfTodos(++todocount, "masoom", "Java FullStack", LocalDate.now().plusYears(1), false));
        todo.add(new ListOfTodos(++todocount, "masoom", "Python FullStack", LocalDate.now().plusYears(2), true));

    }

    public List<ListOfTodos> findByUsername(String username){
         Predicate<? super ListOfTodos> predicate
                 = listOfTodos -> listOfTodos.getUsername().equalsIgnoreCase(username);
        return todo.stream().filter(predicate).toList();
    }
    public void addTodo(String username, String description, LocalDate  date, boolean done){
        todo.add(new ListOfTodos(++todocount, username, description, date, done));
    }
    public void deleteById(int id){
       // todo.remove(id);
        // or
        Predicate<? super ListOfTodos> predicate = listOfTodos -> listOfTodos.getId()==id;
        todo.removeIf(predicate);
    }

    public ListOfTodos findById(int id) {
        Predicate<? super ListOfTodos> predicate = listOfTodos -> listOfTodos.getId()==id;
        ListOfTodos listOfTodos = todo.stream().filter(predicate).findFirst().get();
        return listOfTodos;
    }
    public void updateTodo(ListOfTodos listOfTodos){
        deleteById(listOfTodos.getId());
        todo.add(listOfTodos);
    }
}
