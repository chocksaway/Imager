package com.chocksaway.imager.controller;

import com.chocksaway.imager.domain.Todo;
import com.chocksaway.imager.service.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {
    private final ToDoService toDoService;

    public TodoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }


    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = toDoService.findByUsername("milesd");
        model.addAttribute("todos", todos);

        return "list-todos";
    }

    @RequestMapping("add-todos")
    public String showNewTodoPage() {
        return "add-todos";
    }
}
