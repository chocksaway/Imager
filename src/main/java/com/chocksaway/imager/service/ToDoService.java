package com.chocksaway.imager.service;

import com.chocksaway.imager.domain.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {
    private static final List<Todo> todoList = new ArrayList<>();
    static {
        todoList.add(new Todo(1, "milesd", "Learn AWS",
                LocalDate.now().plusYears(1), false));
        todoList.add(new Todo(1, "milesd", "Learn DevOps",
                LocalDate.now().plusYears(2), false));
        todoList.add(new Todo(1, "milesd", "Learn full stack development",
                LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username) {
        return todoList;
    }
}
