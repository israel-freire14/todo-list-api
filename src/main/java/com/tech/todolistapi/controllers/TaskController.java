package com.tech.todolistapi.controllers;

import com.tech.todolistapi.entities.Task;
import com.tech.todolistapi.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task){
        return  taskService.createTask(task);
    }

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> readAllTasks(){
        return taskService.listAllTasks();
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> readTaskById(@PathVariable(value = "id") Long id){
        return taskService.readTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable(value = "id") Long id, @RequestBody Task task) {
        return taskService.updateTaskById(task,id);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deteteTaskById(@PathVariable(value = "id") Long id){
        return taskService.deleteById(id);
    }

}
