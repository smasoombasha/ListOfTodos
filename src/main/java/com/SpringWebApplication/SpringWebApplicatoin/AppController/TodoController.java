package com.SpringWebApplication.SpringWebApplicatoin.AppController;

import com.SpringWebApplication.SpringWebApplicatoin.AppService.TodoService;
import com.SpringWebApplication.SpringWebApplicatoin.Beans.ListOfTodos;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@Controller
public class TodoController {
    private TodoService todoService;
    // dependency through the constructor
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("todo-list")
    public String showTodoList(ModelMap mp){
       // String username = getUname(mp);
        List<ListOfTodos> todo = todoService.findByUsername("masoom");
        mp.addAttribute("todo",todo);
       // mp.addAttribute("uname", getUname(mp));
        return "todolist";
    }
//    private static String getUname(ModelMap mp) { // getting username from login details
//        Authentication authentication = // SpringSecurity Inbuilt interface
//                SecurityContextHolder.getContext().getAuthentication();// this also inbuilt classes
//        return  authentication.getName();
//        // return (String) mp.get("uname");
//    }

    // here we done Command Bean(Form backing Object). 2way binding from a Bean to the form & form to the bean
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showAddNewTodo(ModelMap mp, ListOfTodos listOfTodos){
        ListOfTodos listOfTodos1 = new ListOfTodos(0,  "masoom", " ", listOfTodos.getDATE_COLUMN(), false);
        mp.put("listOfTodos", listOfTodos1);
        return "addTodo";
    }
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap mp, @Valid ListOfTodos listOfTodos, BindingResult result){
        if(result.hasErrors()){
            return "addTodo";
        }else {
            //String username = getUname();
            todoService.addTodo("masoom", listOfTodos.getDescription(), listOfTodos.getDATE_COLUMN(),false);
            return "redirect:todo-list";
        }

    }
    @RequestMapping(value = "delete-todo" )
    public String deleteTodo(@RequestParam int id){
        todoService.deleteById(id);
        return "redirect:todo-list";
    }
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap mp){
        ListOfTodos listOfTodos = todoService.findById(id);
        mp.addAttribute("listOfTodos",listOfTodos);
        return "addTodo";
    }
    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap mp, @Valid ListOfTodos listOfTodos, BindingResult result){
        if(result.hasErrors()){
            return "addTodo";
        }else {
            listOfTodos.setUsername("masoom");
            todoService.updateTodo(listOfTodos);
            // String username = getUname(mp);
            return "redirect:todo-list";
        }

    }
}
