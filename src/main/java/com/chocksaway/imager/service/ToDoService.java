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
    public static int todoCount = 0;
    static {
        todoList.add(new Todo(1, "milesd", "Learn AWS 12345",
                LocalDate.now().plusYears(1), false));
        todoList.add(new Todo(2, "milesd", "Learn DevOps",
                LocalDate.now().plusYears(2), false));
        todoList.add(new Todo(3, "milesd", "Learn full stack development",
                LocalDate.now().plusYears(3), false));
        todoList.add(new Todo(12, "milesd", "Learn AWS in 5 minutes",
                LocalDate.now().plusYears(3), false));

        todoCount = todoList.size();
    }

    public List<Todo> findByUsername(String username) {
        return todoList;
    }

    public void addTodo(String username, String description, LocalDate localDate, boolean done) {
        Todo todo = new Todo(++todoCount, username, description, localDate, done);
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
