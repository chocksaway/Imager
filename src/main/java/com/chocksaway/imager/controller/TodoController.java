package com.chocksaway.imager.controller;

import com.chocksaway.imager.domain.Todo;
import com.chocksaway.imager.service.PhotoService;
import com.chocksaway.imager.service.ToDoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {
    private final ToDoService toDoService;
    private final PhotoService photoService;

    public TodoController(ToDoService toDoService, PhotoService photoService) {
        this.toDoService = toDoService;
        this.photoService = photoService;
    }


    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = toDoService.findByUsername("milesd");
        model.addAttribute("todos", todos);

        return "list-todos";
    }

    @RequestMapping(value ="delete-todo")
    public String deleteTodo(@RequestParam int id) {
        toDoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value ="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = toDoService.findById(id);
        model.addAttribute("todo", todo);
        return "add-todos";
    }


    @RequestMapping(value = "/photo", method = RequestMethod.GET)
    public String getPhoto(@RequestParam String id, Model model) {
        final var photo = photoService.getPhoto(id);

        if (photo.isEmpty()) {
            return "view-photo";
        }

        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.get().getImage().getData()));

        return "view-photo";
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
        var username = (String) model.getAttribute("name");
        var todo = Todo.builder().id(0)
                .username(username)
                .description("")
                .targetDate(LocalDate.now().plusYears(1))
                .done(false)
                .build();

        model.put("todo", todo);
        return "add-todos";
    }


    @RequestMapping(value = "add-todos", method = RequestMethod.POST)
    public String addNewTodo(@RequestParam("image") MultipartFile image,
                            @Valid Todo todo,
                            BindingResult result,
                            ModelMap model) throws IOException {
        if (result.hasErrors()) {
            return "add-todos";
        }
        var username = (String) model.get("username");
        var photoId = photoService.addPhoto(todo.getDescription(), image);
        toDoService.addTodo(username,
                todo.getDescription(),
                todo.getTargetDate(),
                false, photoId);

        return "redirect:list-todos";
    }





}
