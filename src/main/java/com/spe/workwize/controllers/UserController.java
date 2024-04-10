package com.spe.workwize.controllers;

import com.spe.workwize.config.JwtService;
import com.spe.workwize.customModel.AuthToken;
import com.spe.workwize.customModel.UserModel;
import com.spe.workwize.models.User;
import com.spe.workwize.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger("jairu");

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody Map<String,String> payload) throws AuthenticationException {
        try {
            User user = userService.generateToken(payload);
            User userDetails = new User();
            userDetails.setUsername(user.getUsername());
            List<String> rolesList= new ArrayList<>();
            user.getRoles().forEach(role->{
                rolesList.add(role.getName());
            });
            final String token = jwtService.generateToken(userDetails, rolesList);
            logger.info("[UserController] - [Generate Token]");
            return ResponseEntity.ok(new AuthToken(token));
        }catch (Exception e){
            logger.error("[UserController] - [Error in Generate Token]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody Map<String,String> payload){
        try {
            UserModel userModel = userService.saveUser(payload);
            logger.info("[UserController] - [Save User]");
            return ResponseEntity.ok(userModel);
        }catch (Exception e){
            logger.error("[UserController] - [Error in Save User]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> saveUserA(@RequestBody Map<String,String> payload){
        try {
            UserModel userModel = userService.saveUser(payload);
            return ResponseEntity.ok(userModel);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value="/managerping", method = RequestMethod.GET)
    public ResponseEntity<?> managerPing(){
        try {
            Map<String, String> res = new HashMap<>();
            res.put("msg", "Only Manager Can Read This");
            logger.info("[UserController] - [Manager Ping]");
            return ResponseEntity.ok(res);
        }catch (Exception e){
            logger.error("[UserController] - [Error in Manager Ping]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/adminping", method = RequestMethod.GET)
    public ResponseEntity<?> adminPing(){
        try {
            Map<String, String> res = new HashMap<>();
            res.put("msg", "Only admin Can Read This");
            logger.info("[UserController] - [Admin Ping]");
            return ResponseEntity.ok(res);
        }catch (Exception e){
            logger.error("[UserController] - [Error in Admin Ping]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value="/employeeping", method = RequestMethod.GET)
    public ResponseEntity<?> employeePing(){
        try {
            Map<String, String> res = new HashMap<>();
            res.put("msg", "Only Employee Can Read This");
            logger.info("[UserController] - [Employee Pin]");
            return ResponseEntity.ok(res);
        }catch (Exception e){
            logger.error("[UserController] - [Error in Employee Pin]");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
