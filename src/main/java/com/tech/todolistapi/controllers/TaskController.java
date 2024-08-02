package com.tech.todolistapi.controllers;

import com.google.gson.Gson;
import com.tech.todolistapi.entities.Task;
import com.tech.todolistapi.services.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {
    private static final Logger logger =  LogManager.getLogger(TaskController.class.toString());
    private  static final Gson GSON = new Gson();

    private TaskService taskService;

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task){
        logger.info("Creating a new task with info [{}]", GSON.toJson(task));
        return  taskService.createTask(task);
    }

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> readAllTasks(){
        log.info("Getting all tasks registered");

        return taskService.listAllTasks();
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> readTaskById(@PathVariable(value = "id") Long id){
        log.info("Searching task by id [{}]", id);
        return taskService.readTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable(value = "id") Long id, @RequestBody Task task) {
        log.info("Updating task by id [{}] and the new info are: [{}]", id, GSON.toJson(task));
        return taskService.updateTaskById(task,id);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deteteTaskById(@PathVariable(value = "id") Long id){
        log.info("Excluding task by id [{}]", id);
        return taskService.deleteById(id);
    }

}
