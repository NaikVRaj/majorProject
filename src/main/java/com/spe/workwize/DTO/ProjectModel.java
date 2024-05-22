package com.spe.workwize.DTO;

import com.spe.workwize.bean.EffortTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectModel {
    private Long projectId;
    private String projectName;
    private String description;
    private Set<UserModel> users;
    private Set<TaskModel> tasks;
    private EffortTable effortTable;
}

