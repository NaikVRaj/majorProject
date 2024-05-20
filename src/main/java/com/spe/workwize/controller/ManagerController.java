package com.spe.workwize.controller;

import com.spe.workwize.bean.Project;
import com.spe.workwize.customModel.ProjectModel;
import com.spe.workwize.customModel.UserModel;
import com.spe.workwize.service.manager.ManagerService;
import com.spe.workwize.utils.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/manager")
public class ManagerController {


    private static final Logger logger = LogManager.getLogger("jairu");

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/addProject")
    public ResponseEntity<?> addProject(@RequestBody Map<String,String> payload)
    {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            Project res = managerService.addProject(payload, username);
            res = managerService.initializeEffortTable(res);
            logger.info("[ManagerController] - [Add Project]");
            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
        }catch (Exception e){
            logger.error("[ManagerController] - [Error in Add Project]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/updateProject")
    public ResponseEntity<?> updateProject(@RequestBody Map<String,String> payload)
    {
        try {
            Project res = managerService.updateProject(payload);
            logger.info("[ManagerController] - [Update Project]");
            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
        }catch (Exception e){
            logger.error("[ManagerController] - [Error in Update Project]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/removeProject")
    public ResponseEntity<?> removeProject(@RequestParam Map<String,String> param)
    {
        try {
            Map<String ,String> res = managerService.removeProject(param);
            logger.info("[ManagerController] - [Remove Project]");
            return ResponseEntity.ok(res);
        }catch (Exception e){
            logger.error("[ManagerController] - [Error in Remove Project]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/getAllProject")
    public ResponseEntity<?> getAllProject()
    {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            List<Project> projects = managerService.getAllProject(username);
            List<ProjectModel> projectModels = new ArrayList<>();
            for (Project project : projects) {
                projectModels.add(Constant.getModelMapper().map(project, ProjectModel.class));
            }
            logger.info("[ManagerController] - [Get All Project By Manager]");
            return ResponseEntity.ok(projectModels);
        }catch (Exception e){
            logger.error("[ManagerController] - [Error in Get All Project]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/getFreeEmployee")
    public ResponseEntity<?> getFreeEmployee(@RequestParam Map<String,String> param)
    {
        try {
            List<UserModel> freeEmployee = managerService.getFreeEmployee(param);
            logger.info("[ManagerController] - [Get Free Employee]");
            return ResponseEntity.ok(freeEmployee);
        }catch (Exception e){
            logger.error("[ManagerController] - [Error in Get Free Employee]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/addUserToProject")
    public ResponseEntity<?> addUserToProject(@RequestBody Map<String,Object> payload)
    {
        try {
            Project res = managerService.addUserToProject(payload);
            logger.info("[ManagerController] - [Add User TO Project]");
            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
        }catch (Exception e){
            logger.error("[ManagerController] - [Error in Add User TO Project]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/removeUserFromProject")
    public ResponseEntity<?> removeUserFromProject(@RequestParam Map<String,String> param)
    {
        try {
            Project res = managerService.removeUserFromProject(param);
            logger.info("[ManagerController] - [Remove User From Project]");
            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
        }catch (Exception e){
            logger.error("[ManagerController] - [Remove User From Project]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/addTaskToProject")
    public ResponseEntity<?> addTaskToProject(@RequestBody Map<String,String> payload)
    {
        try {
            Project res = managerService.addTaskToProject(payload);
            logger.info("[ManagerController] - [Add Task To Project]");
            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
        }catch (Exception e){
            logger.error("[ManagerController] - [Error in Add Task To Project]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/removeTaskFromProject")
    public ResponseEntity<?> removeTaskFromProject(@RequestParam Map<String,String> param)
    {
        try {
            Project res = managerService.removeTaskFromProject(param);
            logger.info("[ManagerController] - [Remove Task From Project]");
            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
        }catch (Exception e){
            logger.error("[ManagerController] - [Error in Remove Task From Project]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/updateEffortTable")
    public ResponseEntity<?> updateEffortTable(@RequestBody Map<String,String> payload)
    {
        try {
            Project res = managerService.updateEffortTable(payload);
            logger.info("[ManagerController] - [Update Effort Table]");
            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
        }catch (Exception e){
            logger.error("[ManagerController] - [Error in Update Effort Table]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


//package com.spe.workwize.controller;
//
//import com.spe.workwize.bean.Project;
//import com.spe.workwize.customModel.ProjectModel;
//import com.spe.workwize.customModel.UserModel;
//import com.spe.workwize.service.manager.ManagerService;
//import com.spe.workwize.utils.Constant;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@CrossOrigin("*")
//@RestController
//@RequestMapping("/manager")
//public class ManagerController {
//
//
//    private static final Logger logger = LogManager.getLogger("jairu");
//
//    private final ManagerService managerService;
//
//    @Autowired
//    public ManagerController(ManagerService managerService) {
//        this.managerService = managerService;
//    }
//
//    @PreAuthorize("hasAuthority('MANAGER')")
//    @PostMapping("/addProject")
//    public ResponseEntity<?> addProject(@RequestBody Map<String,String> payload)
//    {
//        try {
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            String username = userDetails.getUsername();
//            Project res = managerService.addProject(payload, username);
//            res = managerService.initializeEffortTable(res);
//            logger.info("[ManagerController] - [Add Project]");
//            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
//        }catch (Exception e){
//            logger.error("[ManagerController] - [Error in Add Project]");
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PreAuthorize("hasAuthority('MANAGER')")
//    @PostMapping("/updateProject")
//    public ResponseEntity<?> updateProject(@RequestBody Map<String,String> payload)
//    {
//        try {
//            Project res = managerService.updateProject(payload);
//            logger.info("[ManagerController] - [Update Project]");
//            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
//        }catch (Exception e){
//            logger.error("[ManagerController] - [Error in Update Project]");
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PreAuthorize("hasAuthority('MANAGER')")
//    @GetMapping("/removeProject")
//    public ResponseEntity<?> removeProject(@RequestParam Map<String,String> param)
//    {
//        try {
//            Map<String ,String> res = managerService.removeProject(param);
//            logger.info("[ManagerController] - [Remove Project]");
//            return ResponseEntity.ok(res);
//        }catch (Exception e){
//            logger.error("[ManagerController] - [Error in Remove Project]");
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PreAuthorize("hasAuthority('MANAGER')")
//    @GetMapping("/getAllProject")
//    public ResponseEntity<?> getAllProject()
//    {
//        try {
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            String username = userDetails.getUsername();
//            List<Project> projects = managerService.getAllProject(username);
//            List<ProjectModel> projectModels = new ArrayList<>();
//            for (Project project : projects) {
//                projectModels.add(Constant.getModelMapper().map(project, ProjectModel.class));
//            }
//            logger.info("[ManagerController] - [Get All Project By Manager]");
//            return ResponseEntity.ok(projectModels);
//        }catch (Exception e){
//            logger.error("[ManagerController] - [Error in Get All Project]");
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PreAuthorize("hasAuthority('MANAGER')")
//    @GetMapping("/getFreeEmployee")
//    public ResponseEntity<?> getFreeEmployee(@RequestParam Map<String,String> param)
//    {
//        try {
//            List<UserModel> freeEmployee = managerService.getFreeEmployee(param);
//            logger.info("[ManagerController] - [Get Free Employee]");
//            return ResponseEntity.ok(freeEmployee);
//        }catch (Exception e){
//            logger.error("[ManagerController] - [Error in Get Free Employee]");
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PreAuthorize("hasAuthority('MANAGER')")
//    @PostMapping("/addUserToProject")
//    public ResponseEntity<?> addUserToProject(@RequestBody Map<String,Object> payload)
//    {
//        try {
//            Project res = managerService.addUserToProject(payload);
//            logger.info("[ManagerController] - [Add User TO Project]");
//            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
//        }catch (Exception e){
//            logger.error("[ManagerController] - [Error in Add User TO Project]");
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PreAuthorize("hasAuthority('MANAGER')")
//    @GetMapping("/removeUserFromProject")
//    public ResponseEntity<?> removeUserFromProject(@RequestParam Map<String,String> param)
//    {
//        try {
//            Project res = managerService.removeUserFromProject(param);
//            logger.info("[ManagerController] - [Remove User From Project]");
//            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
//        }catch (Exception e){
//            logger.error("[ManagerController] - [Remove User From Project]");
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//
//    @PreAuthorize("hasAuthority('MANAGER')")
//    @PostMapping("/addTaskToProject")
//    public ResponseEntity<?> addTaskToProject(@RequestBody Map<String,String> payload)
//    {
//        try {
//            Project res = managerService.addTaskToProject(payload);
//            logger.info("[ManagerController] - [Add Task To Project]");
//            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
//        }catch (Exception e){
//            logger.error("[ManagerController] - [Error in Add Task To Project]");
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PreAuthorize("hasAuthority('MANAGER')")
//    @GetMapping("/removeTaskFromProject")
//    public ResponseEntity<?> removeTaskFromProject(@RequestParam Map<String,String> param)
//    {
//        try {
//            Project res = managerService.removeTaskFromProject(param);
//            logger.info("[ManagerController] - [Remove Task From Project]");
//            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
//        }catch (Exception e){
//            logger.error("[ManagerController] - [Error in Remove Task From Project]");
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PreAuthorize("hasAuthority('MANAGER')")
//    @PostMapping("/updateEffortTable")
//    public ResponseEntity<?> updateEffortTable(@RequestBody Map<String,String> payload)
//    {
//        try {
//            Project res = managerService.updateEffortTable(payload);
//            logger.info("[ManagerController] - [Update Effort Table]");
//            return ResponseEntity.ok(Constant.getModelMapper().map(res, ProjectModel.class));
//        }catch (Exception e){
//            logger.error("[ManagerController] - [Error in Update Effort Table]");
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//}
