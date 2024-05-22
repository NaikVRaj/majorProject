package com.spe.workwize.service.manager;

import com.spe.workwize.bean.*;
import com.spe.workwize.DTO.UserModel;
import com.spe.workwize.repository.*;
import com.spe.workwize.service.user.UserService;
import com.spe.workwize.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "managerService")
public class ManagerServiceImpl implements ManagerService {

    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    private final TaskStatusLuRepository taskStatusLuRepository;

    private final TaskRepository taskRepository;

    private final EffortRepository effortRepository;

    private final UserService userService;


    @Autowired
    public ManagerServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, TaskStatusLuRepository taskStatusLuRepository, TaskRepository taskRepository, EffortRepository effortRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskStatusLuRepository = taskStatusLuRepository;
        this.taskRepository = taskRepository;
        this.effortRepository = effortRepository;
        this.userService = userService;
    }

    @Override
    public List<Project> getAllProject(String username)
    {
        User user = userService.findOne(username);
        return new ArrayList<>(projectRepository.getAllProjectByUserId(user.getUserId()));
    }

    @Override
    public Project addProject(Map<String,String> payload,String username)
    {
        Project project = new Project();
        project.setProjectName(payload.get("name"));
        project.setDescription(payload.get("description"));
        User user = userService.findOne(username);
        Set<User> usersSet = new HashSet<>();
        usersSet.add(user);
        project.setUsers(usersSet);
        Project project1 = projectRepository.save(project);
        return projectRepository.findByProjectId(project1.getProjectId());
    }

    @Override
    public Project updateProject(Map<String,String> payload)
    {
        Project project = projectRepository.findByProjectId(Long.parseLong(payload.get("id")));
        project.setDescription(payload.get("description"));
        return projectRepository.save(project);
    }

    @Override
    public Map<String,String> removeProject(Map<String,String> param)
    {
        projectRepository.deleteById(Long.parseLong(param.get("projectId")));
        Map<String,String> res = new HashMap<>();
        res.put("msg","Project Remove");
        return  res;
    }

    @Override
    public List<UserModel> getFreeEmployee(Map<String,String> param)
    {
        Long projectId = Long.parseLong(param.get("projectId"));
        List<User> freeEmployees = userRepository.getFreeEmployee(projectId);
        List<UserModel> userModels = new ArrayList<>();
        for (User freeEmployee : freeEmployees) {
            userModels.add(Constant.getModelMapper().map(freeEmployee, UserModel.class));
        }
        return userModels;
    }

    @Override
    public Project addUserToProject(Map<String,Object> payload)
    {
        Long projectId = Long.valueOf((Integer)payload.get("projectId"));
        List<Integer> userIds = (List<Integer>)(payload.get("userId"));
        Project project = projectRepository.findByProjectId(projectId);
        Set<User> userSet = project.getUsers();
        userIds.forEach((userId) -> userSet.add((userRepository.findByUserId(Long.valueOf(userId)))));
        project.setUsers(userSet);
        return projectRepository.save(project);
    }

    @Override
    public Project removeUserFromProject(Map<String,String> param)
    {
        Project project = projectRepository.findByProjectId(Long.parseLong(param.get("projectId")));
        User user = userRepository.findByUserId(Long.parseLong(param.get("userId")));
        Set<User> userSet = project.getUsers();
        userSet.remove(user);
        project.setUsers(userSet);
        return projectRepository.save(project);
    }



    @Override
    public Project addTaskToProject(Map<String,String> payload)
    {
        Project project = projectRepository.findByProjectId(Long.parseLong(payload.get("projectId")));
        User user = userRepository.findByUserId(Long.parseLong(payload.get("userId")));
        TaskStatusLu taskStatusLu = taskStatusLuRepository.findByTaskStatusLuId(1L);
        Task task = new Task();
        task.setTaskName(payload.get("name"));
        task.setDescription(payload.get("desc"));
        task.setProject(project);
        task.setUser(user);
        task.setStatusLu(taskStatusLu);
        taskRepository.save(task);
        project = projectRepository.findByProjectId(Long.parseLong(payload.get("projectId")));
        return project;
    }

    @Override
    public Project removeTaskFromProject(Map<String,String> param)
    {
        taskRepository.deleteById(Long.parseLong(param.get("taskId")));
        return projectRepository.findByProjectId(Long.parseLong(param.get("projectId")));
    }



    @Override
    public Project updateEffortTable(Map<String,String> payload)
    {
        EffortTable effort = effortRepository.findByEffortTableId(Long.parseLong(payload.get("effortId")));
        effort.setRequirementAnalysisHours(Integer.parseInt(payload.get("rah")));
        effort.setDesigningHours(Integer.parseInt(payload.get("dh")));
        effort.setCodingHours(Integer.parseInt(payload.get("ch")));
        effort.setTestingHours(Integer.parseInt(payload.get("th")));
        effort.setProjectManagementHours(Integer.parseInt(payload.get("pmh")));
        effortRepository.save(effort);
        return projectRepository.findByProjectId(Long.parseLong(payload.get("projectId")));
    }

    @Override
    public Project initializeEffortTable(Project project)
    {
        EffortTable effort = new EffortTable();
        effort.setRequirementAnalysisHours(0);
        effort.setDesigningHours(0);
        effort.setCodingHours(0);
        effort.setTestingHours(0);
        effort.setProjectManagementHours(0);
        effort.setProject(project);
        effortRepository.save(effort);
        return projectRepository.findByProjectId(project.getProjectId());
    }
}