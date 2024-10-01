package org.project.task.service;

import org.modelmapper.ModelMapper;
import org.project.task.entity.Task;
import org.project.task.entity.Users;
import org.project.task.exception.ApiException;
import org.project.task.exception.TaskNotFound;
import org.project.task.exception.UserNotFound;
import org.project.task.payload.TaskDto;
import org.project.task.repository.TaskRepository;
import org.project.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public TaskDto saveTask(Long userid, TaskDto taskDto) {
        Users user=userRepository.findById(userid).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d not found",userid))
        );
        Task task=modelMapper.map(taskDto, Task.class);
        task.setUser(user);
        //After setting the user, we are storing in the db
        Task savedTask=taskRepository.save(task);
        return modelMapper.map(savedTask,TaskDto.class);
    }

    @Override
    public List<TaskDto> getAllTasks(Long userid) {
        userRepository.findById(userid).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d not found",userid))
        );
        List<Task> tasks=taskRepository.findAllByUserId(userid);
        return tasks.stream().map(
                task -> modelMapper.map(task, TaskDto.class)//to map one obj to other obj-->stream
        ).collect(Collectors.toList());
    }

    @Override
    public TaskDto getTask(Long userid, Long taskid) {
        Users users=userRepository.findById(userid).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d not found",userid))
        );
        Task task=taskRepository.findById(taskid).orElseThrow(
                ()->new TaskNotFound(String.format("Task Id %d not found",taskid))
        );
        if(users.getId()!= task.getUser().getId()) {
            throw new ApiException(String.format("Task Id %d is not belongs to User Id %d",taskid,userid));
        }
        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public void deleteTask(Long userid, Long taskid) {
        Users users=userRepository.findById(userid).orElseThrow(
                ()->new UserNotFound(String.format("User Id %d not found",userid))
        );
        Task task=taskRepository.findById(taskid).orElseThrow(
                ()->new TaskNotFound(String.format("Task Id %d not found",taskid))
        );
        if(users.getId() !=task.getUser().getId()) {
            throw new ApiException(String.format("Task Id %d is not belongs to User Id %d",taskid,userid));
        }

        taskRepository.deleteById(taskid);
    }
}
