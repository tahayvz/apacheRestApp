package com.tahayavuz.apacherestapp.services;

import com.tahayavuz.api.domain.User;

import java.util.List;
import java.util.Map;

public interface ApiService {

    List<Object> getRepos();
    Map<String, Integer> getContributors(int repo);
    User getUsers(int repo, int userIndex);
}
