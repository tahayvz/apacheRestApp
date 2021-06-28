package com.tahayavuz.apacherestapp.services;

import java.util.List;
import java.util.Map;

public interface ApiService {

    List<Object> getRepos();
    Map<String, Integer> getContributors(int repo);
}
