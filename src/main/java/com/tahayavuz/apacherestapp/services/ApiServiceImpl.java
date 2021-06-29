package com.tahayavuz.apacherestapp.services;

import com.tahayavuz.api.domain.Contributors;
import com.tahayavuz.api.domain.Repositories;
import com.tahayavuz.api.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;

    private final String api_url;
    List<Object> repoNames =new ArrayList<>();

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

    @Override
    public Map<String, Integer> getContributors(int repo) {
        if(repoNames.isEmpty()){
            repoNames.addAll( getRepos());
        }
        Map<String, Integer>  hm = new HashMap();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(api_url + "/repos/apache/" + repoNames.get(repo).toString() + "/contributors");

        ResponseEntity<List<Contributors>> responseEntity =
                restTemplate.exchange(
                        uriBuilder.toUriString(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Contributors>>() {
                        }
                );
        List<Contributors> contributorsData = responseEntity.getBody();

        for (int i = 0; i < contributorsData.size(); i++) {
            hm.put(contributorsData.get(i).getLogin(), contributorsData.get(i).getContributions());
        }
        hm = sortByValue(hm);
        hm = hm.entrySet().stream()
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));

        return hm;
    }

    @Override
    public User getUsers(int repo, int userIndex) {
        Set<String> userList = new HashSet<>();
        //take usernames
        Map<String, Integer> m = new HashMap<>();
        m.putAll(getContributors(repo));

        userList.addAll(m.keySet());

        String[] userArray = userList.toArray(new String[userList.size()]);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(api_url + "/users/" + userArray[userIndex]);

        User myUser = restTemplate.getForObject(uriBuilder.toUriString(), User.class);

        WriteFile fileObject = new WriteFile();
        fileObject.write(repoNames.get(repo).toString(),myUser,m.get( userArray[userIndex]));

        return myUser;
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

    //sort elements by values
    Map<String, Integer> sortByValue(Map map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        //prints the sorted HashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
