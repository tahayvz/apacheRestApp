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
import sun.reflect.generics.tree.Tree;

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
        repoList.forEach(x -> hm.put(x.getName(), x.getForks_count()));

        return (List<Object>) hm.values().stream()
                .sorted(Comparator.reverseOrder())
                .limit(5)
                .map(x -> getKey(hm, x))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> getContributors(int repo) {
        if(repoNames.isEmpty()){
            repoNames.addAll( getRepos());
        }
        Map<String, Integer>  hm = new LinkedHashMap<>();

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

        Iterator<Contributors> itrContributorsData = contributorsData.iterator();
        while (itrContributorsData.hasNext()) {
            hm.put(itrContributorsData.next().getLogin(), itrContributorsData.next().getContributions());
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
        LinkedHashSet <String> userList = new LinkedHashSet <>();
        //take usernames
        LinkedHashMap<String, Integer> m = new LinkedHashMap<>();
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
