package org.project.task.service;

import org.project.task.payload.TaskDto;

import java.util.List;

public interface TaskService {

    public TaskDto saveTask(Long userid, TaskDto taskDto);
    public List<TaskDto> getAllTasks(Long userid);

    public TaskDto getTask(Long userid,Long taskid);
    public void deleteTask(Long userid,Long taskid);
}
