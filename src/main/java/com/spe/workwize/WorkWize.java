package com.spe.workwize;

import com.spe.workwize.service.init.AddAdminImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkWize {
    public static void main(String[] args) {
        SpringApplication.run(WorkWize.class, args);
        AddAdminImpl addAdmin = new AddAdminImpl();
        try{
            addAdmin.postToApi();
        }
        catch(Exception e){
            System.out.println("e = " + e);
        }
    }
}
