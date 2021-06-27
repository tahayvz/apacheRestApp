package com.tahayavuz.apacherestapp.services;

import com.tahayavuz.api.domain.Repositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;

    private final String api_url;
    List<String> usernameMaxContributor = new ArrayList<>();
    String username;
    String loginName;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    @Override
    public List<Object> getRepos() {

        HashMap hm = new HashMap();
        List<Object> maxForkRepoList = new ArrayList<>();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(api_url + "/orgs/apache/repos");
        ResponseEntity<List<Repositories>> responseEntity =
                restTemplate.exchange(
                        uriBuilder.toUriString(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Repositories>>() {
                        }
                );
        List<Repositories> repoList = responseEntity.getBody();

        for (int i = 0; i < repoList.size(); i++) {
            hm.put(repoList.get(i).getName(), repoList.get(i).getForks_count());
        }
        List<Integer> forksCountsList = new ArrayList<Integer>(hm.values());
        Collections.sort(forksCountsList);
        //get 5 max fork repos
        forksCountsList = forksCountsList.subList(repoList.size() - 5, repoList.size());
        for (int i = 0; i < forksCountsList.size(); i++) {
            maxForkRepoList.add(getKey(hm, forksCountsList.get(i)));
        }

        return maxForkRepoList;
    }

    public static <K, V> K getKey(Map<K, V> map, V value)
    {
        for (Map.Entry<K, V> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
