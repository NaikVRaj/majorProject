package com.spe.workwize.service.Employee;

import com.spe.workwize.bean.Project;
import com.spe.workwize.bean.Task;
import com.spe.workwize.bean.TaskStatusLu;
import com.spe.workwize.bean.User;
import com.spe.workwize.repository.ProjectRepository;
import com.spe.workwize.repository.TaskRepository;
import com.spe.workwize.repository.TaskStatusLuRepository;
import com.spe.workwize.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private final UserService userService;

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    private final TaskStatusLuRepository taskStatusLuRepository;

    @Autowired
    public EmployeeServiceImpl(UserService userService, ProjectRepository projectRepository, TaskRepository taskRepository, TaskStatusLuRepository taskStatusLuRepository) {
        this.userService = userService;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.taskStatusLuRepository = taskStatusLuRepository;
    }

    @Override
    public List<Project> getAllProjects(String username)
    {
        User user = userService.findOne(username);
        return new ArrayList<>(projectRepository.getAllProjectByUserId(user.getUserId()));
    }

    @Override
    public List<Task> getAllTaskByProjectByUser(Map<String,String> param,String username)
    {
        Long projectId = Long.parseLong(param.get("projectId"));
        User user = userService.findOne(username);
        return taskRepository.findAllByProject_ProjectIdAndUser_UserId(projectId,user.getUserId());
    }

    @Override
    public List<Task> updateTaskStatus(Map<String,String> payload,String username)
    {
        Long taskId = Long.parseLong(payload.get("taskId"));
        Long statusLuId  = Long.parseLong(payload.get("statusId"));
        Long projectId = Long.parseLong(payload.get("projectId"));
        Task task = taskRepository.findByTaskId(taskId);
        TaskStatusLu taskStatusLu = taskStatusLuRepository.findByTaskStatusLuId(statusLuId);
        task.setStatusLu(taskStatusLu);
        taskRepository.save(task);
        User user = userService.findOne(username);
        return taskRepository.findAllByProject_ProjectIdAndUser_UserId(projectId,user.getUserId());
    }
}
