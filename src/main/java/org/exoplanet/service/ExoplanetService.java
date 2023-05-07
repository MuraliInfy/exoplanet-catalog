package org.exoplanet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.exoplanet.dto.Exoplanet;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExoplanetService {

    private final String uri = "https://gist.githubusercontent.com/joelbirchler/66cf8045fcbb6515557347c05d789b4a/raw/9a196385b44d4288431eef74896c0512bad3defe/exoplanets";

    /*To fetch the JSON dataset*/
    public List<Exoplanet> getExoPlanetList() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        List<Exoplanet> exoplanetList = mapper.readValue(result, new TypeReference<List<Exoplanet>>() {
        });
        return exoplanetList;

    }
    /*The number of orphan planets (no star)*/
    public void getNumberOfOrphanPlanets(List<Exoplanet> exoplanetList) {
        int count = 0;
        for (Exoplanet exoplanet : exoplanetList) {
            if (exoplanet.getTypeFlag() == 3) {
                count++;
            }
        }
        System.out.println("The number of orphan planets (no star) are " + count);
    }

    /*The name (planet identifier) of the planet orbiting the hottest star*/
    public void getNameOfPlanetOrbitingHottestStar(List<Exoplanet> exoplanetList) {
        Exoplanet exoplanet = new Exoplanet();
        if (!exoplanetList.isEmpty()) {
            exoplanet = exoplanetList.stream()
                    .max(Comparator.comparing(Exoplanet::getHostStarTempK))
                    .orElseThrow(NoSuchElementException::new);
        }
        System.out.println("The name (planet identifier) of the planet orbiting the hottest star is " + exoplanet.getPlanetIdentifier());
    }

    /*A timeline of the number of planets discovered per year grouped by size*/
    public void getTimelineOfPlanets(List<Exoplanet> exoplanetList) {
        List<String> uniqueDiscoveryYear = exoplanetList.stream().filter(o -> !o.getDiscoveryYear().isEmpty())
                .map(Exoplanet::getDiscoveryYear)
                .distinct()
                .collect(Collectors.toList());
        Collections.sort(uniqueDiscoveryYear);
        uniqueDiscoveryYear.forEach(s -> {
            System.out.println("In " + s + " we discovered " + exoplanetList.stream().filter(exoplanet -> exoplanet.getDiscoveryYear().equals(s) && exoplanet.getRadiusJpt() < 1)
                    .count() + " small planets, "
                    + exoplanetList.stream().filter(exoplanet -> exoplanet.getDiscoveryYear().equals(s) && exoplanet.getRadiusJpt() > 1 && exoplanet.getRadiusJpt() < 2)
                    .count() + " medium planets and "
                    + exoplanetList.stream().filter(exoplanet -> exoplanet.getDiscoveryYear().equals(s) && exoplanet.getRadiusJpt() > 2)
                    .count() + " large planets.");
        });
    }
}
