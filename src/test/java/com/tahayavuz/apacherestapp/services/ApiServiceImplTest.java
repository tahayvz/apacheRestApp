package com.tahayavuz.apacherestapp.services;

import com.tahayavuz.api.domain.Repositories;
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
}