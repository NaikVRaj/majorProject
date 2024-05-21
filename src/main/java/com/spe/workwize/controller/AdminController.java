package com.spe.workwize.controller;

import com.spe.workwize.customModel.UserModel;
import com.spe.workwize.service.Admin.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LogManager.getLogger("workwize");

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/getManagerData")
    public ResponseEntity<?> getAllManager()
    {
        try {
            List<UserModel> managers = adminService.getAllManager();
            logger.info("[AdminController] - [Get All Manager]");
            return ResponseEntity.ok(managers);
        }catch(Exception e){
            logger.error("[AdminController] - [Error in Get All Manager]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getEmployeeData")
    public ResponseEntity<?> getAllEmployee()
    {
        try {
            List<UserModel> emps = adminService.getAllEmployee();
            logger.info("[AdminController] - [Get All Employee]");
            return ResponseEntity.ok(emps);
        }catch (Exception e){
            logger.error("[AdminController] - [Error in Get All Employee]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam Map<String,String> param)
    {
        try {
            Map<String,String> res = adminService.deleteUser(param);
            logger.info("[AdminController] - [Delete User]");
            return ResponseEntity.ok(res);
        }catch (Exception e){
            logger.error("[AdminController] - [Error in Delete User]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
