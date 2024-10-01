package org.project.task.controller;

import org.project.task.payload.TaskDto;
import org.project.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/{userid}/tasks")
    public ResponseEntity<TaskDto> saveTask(
            @PathVariable(name = "userid") Long userid,
            @RequestBody TaskDto taskDto
    ){
        return new ResponseEntity<>(taskService.saveTask(userid,taskDto), HttpStatus.CREATED);
    }
    @GetMapping("/{userid}/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks(
            @PathVariable(name="userid") Long userid
    ){
        return new ResponseEntity<>(taskService.getAllTasks(userid),HttpStatus.OK);
    }

    @GetMapping("/{userid}/tasks/{taskid}")
    public ResponseEntity<TaskDto> getTask(
            @PathVariable(name="userid") Long userid,
            @PathVariable(name = "taskid") Long taskid
    ){
        return new ResponseEntity<>(taskService.getTask(userid,taskid),HttpStatus.OK);
    }
    @DeleteMapping("/{userid}/tasks/{taskid}")
    public ResponseEntity<String> deleteTask(
            @PathVariable(name="userid") Long userid,
            @PathVariable(name = "taskid") Long taskid
    ){
        taskService.deleteTask(userid,taskid);
        return new ResponseEntity<>("Task deleted successfully!!",HttpStatus.OK);
    }
}
