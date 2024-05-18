package com.spe.workwize.service.Employee;

import com.spe.workwize.bean.Project;
import com.spe.workwize.bean.Task;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Project> getAllProjects(String username);
    List<Task> getAllTaskByProjectByUser(Map<String,String> param, String username);
    List<Task> updateTaskStatus(Map<String,String> payload,String username);
}
