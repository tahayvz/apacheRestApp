package com.tahayavuz.apacherestapp.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceImplTest {

    @Autowired
    ApiService apiService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetRepositories() throws Exception {

        List<Object> repositoriesList = apiService.getRepos();

        assertEquals(5, repositoriesList.size());
    }

    @Test
    public void testGetContributors() throws Exception {
        Map<String, Integer> contributors =apiService.getContributors(0);
        Assert.assertEquals(10, contributors.size());

        Map<String, Integer> contributors2 =apiService.getContributors(1);
        Assert.assertEquals(10, contributors2.size());

        Map<String, Integer> contributors3 =apiService.getContributors(2);
        Assert.assertEquals(10, contributors3.size());

        Map<String, Integer> contributors4 =apiService.getContributors(3);
        Assert.assertEquals(10, contributors4.size());

        Map<String, Integer> contributors5 =apiService.getContributors(4);
        Assert.assertEquals(10, contributors5.size());
    }

    @Test
    public void testGetUsers() throws Exception {
        //writing informations into text
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