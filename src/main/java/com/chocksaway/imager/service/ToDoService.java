package com.chocksaway.imager.service;

import com.chocksaway.imager.domain.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    private static final List<Todo> todoList = new ArrayList<>();
    public static int todoCount = 0;
    static {
        todoList.add(new Todo(1, "milesd", "Learn AWS",
                LocalDate.now().plusYears(1), false));
        todoList.add(new Todo(2, "milesd", "Learn DevOps",
                LocalDate.now().plusYears(2), false));
        todoList.add(new Todo(3, "milesd", "Learn full stack development",
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
}
