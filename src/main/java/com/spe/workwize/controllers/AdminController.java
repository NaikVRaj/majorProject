package com.spe.workwize.controllers;

import com.spe.workwize.customModel.UserModel;
import com.spe.workwize.service.Admin.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LogManager.getLogger("jairu");

    private final AdminService adminService;

    @Autowired
    public AdminController (AdminService adminService) {
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
}
