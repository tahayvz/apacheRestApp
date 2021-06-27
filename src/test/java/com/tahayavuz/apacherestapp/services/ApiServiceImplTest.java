package com.tahayavuz.apacherestapp.services;

import com.tahayavuz.api.domain.Repositories;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
}