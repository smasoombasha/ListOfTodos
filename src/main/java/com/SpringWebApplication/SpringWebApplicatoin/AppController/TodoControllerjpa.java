package com.SpringWebApplication.SpringWebApplicatoin.AppController;

import com.SpringWebApplication.SpringWebApplicatoin.Beans.ListOfTodos;
import com.SpringWebApplication.SpringWebApplicatoin.Beans.UniqueCode;
import com.SpringWebApplication.SpringWebApplicatoin.DAO.TodoRepo;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

@Controller
@SessionAttributes("uname")
public class TodoControllerjpa {
    // dependency of repo by fields
    @Autowired
    private TodoRepo repo;

    private static String getUname() { // getting username from login details
        Authentication authentication = // SpringSecurity Inbuilt interface
                SecurityContextHolder.getContext().getAuthentication();// this also inbuilt classes
        return  authentication.getName();
        // return (String) mp.get("uname");
    }
    private Set<Integer> id = new HashSet<>(Set.of(957, 956, 955, 954, 953));



    private UniqueCode uniqueCode;
    public TodoControllerjpa(UniqueCode uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    @RequestMapping(value = "all-todos", method = RequestMethod.GET)
    public String showAllTodojsp() {
         return "allTodos";
    }
    private static final Logger logger = LoggerFactory.getLogger(TodoControllerjpa.class);
    @RequestMapping(value = "submit-code", method = RequestMethod.POST)
    public String showAllTodos(ModelMap mp, @RequestParam String code){
        logger.debug("Received code: {}", code);
        System.out.println(code);
        if(id.contains(parseInt(code))){
            List<ListOfTodos> todo = repo.findAll();
            mp.addAttribute("todo",todo);
            //mp.addAttribute("uname", getUname(mp));
            mp.addAttribute("uname", getUname());
            return "todolist";
        } else {
            mp.put("msg","Pls try Again with correct Unique Code.");
            return "allTodos";
        }
    }

    @RequestMapping("todo-list")
    public String showTodoList(ModelMap mp){
        List<ListOfTodos> todo = repo.findByUsername(getUname());
        mp.addAttribute("todo",todo);
        //mp.addAttribute("uname", getUname(mp));
        mp.addAttribute("uname", getUname());
        return "todolist";
    }

    // here we done Command Bean(Form backing Object). 2way binding from a Bean to the form & form to the bean
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showAddNewTodo(ModelMap mp, ListOfTodos listOfTodos){
        ListOfTodos listOfTodos1 = new ListOfTodos(0,  getUname(), " ", listOfTodos.getDATE_COLUMN(), false);
        mp.put("listOfTodos", listOfTodos1);
        return "addTodo";
    }
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap mp, @Valid ListOfTodos listOfTodos, BindingResult result){
        if(result.hasErrors()){
            return "addTodo";
        }else {
            //String username = getUname();
            //todoService.addTodo(getUname(mp), listOfTodos.getDescription(), listOfTodos.getDate(),false);
            listOfTodos.setUsername(getUname());
            repo.save(listOfTodos);
            return "redirect:todo-list";
        }

    }
    @RequestMapping(value = "delete-todo" )
    public String deleteTodo(@RequestParam int id){
        repo.deleteById(id);
        return "redirect:todo-list";
    }
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap mp){
        ListOfTodos listOfTodos = repo.findById(id).get();
        mp.addAttribute("listOfTodos",listOfTodos);
         return "addTodo";
    }
    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap mp, @Valid ListOfTodos listOfTodos, BindingResult result){
        if(result.hasErrors()){
            return "addTodo";
        }else {
            listOfTodos.setUsername(getUname());
            repo.save(listOfTodos);
             return "redirect:todo-list";
        }

    }
}
