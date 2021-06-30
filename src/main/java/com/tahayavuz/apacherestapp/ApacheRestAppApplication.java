package com.tahayavuz.apacherestapp;

import com.tahayavuz.apacherestapp.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApacheRestAppApplication  implements CommandLineRunner {

    @Autowired
    private ApiService apiService;

    public static void main(String[] args) {
        SpringApplication.run(ApacheRestAppApplication.class, args);
    }

    @Override
    public void run(String...args) throws Exception {
        apiService.getUsers(0,0);
        apiService.getUsers(0,1);
        apiService.getUsers(0,2);
        apiService.getUsers(0,3);
        apiService.getUsers(0,4);
        apiService.getUsers(0,5);
        apiService.getUsers(0,6);
        apiService.getUsers(0,7);
        apiService.getUsers(0,8);
        apiService.getUsers(0,9);
        apiService.getUsers(1,0);
        apiService.getUsers(1,1);
        apiService.getUsers(1,2);
        apiService.getUsers(1,3);
        apiService.getUsers(1,4);
        apiService.getUsers(1,5);
        apiService.getUsers(1,6);
        apiService.getUsers(1,7);
        apiService.getUsers(1,8);
        apiService.getUsers(1,9);
        apiService.getUsers(2,0);
        apiService.getUsers(2,1);
        apiService.getUsers(2,2);
        apiService.getUsers(2,3);
        apiService.getUsers(2,4);
        apiService.getUsers(2,5);
        apiService.getUsers(2,6);
        apiService.getUsers(2,7);
        apiService.getUsers(2,8);
        apiService.getUsers(2,9);
        apiService.getUsers(3,0);
        apiService.getUsers(3,1);
        apiService.getUsers(3,2);
        apiService.getUsers(3,3);
        apiService.getUsers(3,4);
        apiService.getUsers(3,5);
        apiService.getUsers(3,6);
        apiService.getUsers(3,7);
        apiService.getUsers(3,8);
        apiService.getUsers(3,9);
        apiService.getUsers(4,0);
        apiService.getUsers(4,1);
        apiService.getUsers(4,2);
        apiService.getUsers(4,3);
        apiService.getUsers(4,4);
        apiService.getUsers(4,5);
        apiService.getUsers(4,6);
        apiService.getUsers(4,7);
        apiService.getUsers(4,8);
        apiService.getUsers(4,9);
    }
}
