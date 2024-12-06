package com.chocksaway.imager.service;

import com.chocksaway.imager.domain.Todo;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ToDoService {

    private static final List<Todo> todoList = new ArrayList<>();
    public static int todoCount;

    static {
        todoList.add(Todo.builder().id(0).username("milesd").description("Learn AWS").build());

        todoList.add(Todo.builder().id(1).username("milesd").description("Learn AWS 12345").build());

        todoList.add(Todo.builder().id(2).username("milesd").description("Learn DevOps").build());

        todoList.add(Todo.builder().id(3).username("milesd").description("Learn full stack development").build());

        todoList.add(Todo.builder().id(4).username("milesd").description("Learn AWS in 5 minutes").build());

        todoCount = todoList.size();
    }

    public List<Todo> findByUsername(String username) {
        return todoList.stream().filter(each -> each.getUsername().equals(username)).toList();
    }

    public void addTodo(String username, String description, LocalDate localDate, boolean done, String photoId) {
        var todo = Todo.builder().username(username)
                .id(++todoCount)
                .description(description)
                .targetDate(localDate)
                .done(done)
                .photoId(photoId)
                .build();

        todoList.add(todo);
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate =
                each -> each.getId() == id;
        todoList.removeIf(predicate);
    }

    @Cacheable(
            value = "toDoCache",
            key = "#id",
            condition = "#id>10")
    public Todo findById(int id) {
        Predicate<? super Todo> predicate =
                each -> each.getId() == id;
        Optional<Todo> todoOptional = todoList.stream()
                .filter(predicate)
                .findFirst();
        return todoOptional.orElse(null);

    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todoList.add(todo);
    }
}
