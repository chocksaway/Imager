package com.chocksaway.imager.controller;

import com.chocksaway.imager.domain.Todo;
import com.chocksaway.imager.service.ToDoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
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

    @RequestMapping(value ="delete-todo")
    public String deleteTodo(@RequestParam int id, ModelMap model) {
        toDoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value ="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = toDoService.findById(id);
        model.addAttribute("todo", todo);
        return "add-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(@Valid Todo todo, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "add-todos";
        }

        todo.setUsername((String) model.get("username"));
        toDoService.updateTodo(todo);
        return "redirect:list-todos";
    }


    @RequestMapping(value = "add-todos", method = RequestMethod.GET)
    public String addTodos(ModelMap model) {
        String username = (String) model.getAttribute("name");
        Todo todo = new Todo(0, username, "",
                LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "add-todos";
    }

    @RequestMapping(value = "add-todos", method = RequestMethod.POST)
    public String addNewTodo(@Valid Todo todo, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "add-todos";
        }
        var username = (String) model.get("username");
        toDoService.addTodo(username,
                todo.getDescription(),
                LocalDate.now().plusYears(1),
                false);
        return "redirect:list-todos";
    }
}
