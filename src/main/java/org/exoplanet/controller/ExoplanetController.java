package org.exoplanet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.exoplanet.dto.Exoplanet;
import org.exoplanet.service.ExoplanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExoplanetController {

    @Autowired
    private ExoplanetService exoplanetService;

    @GetMapping("/exoplanet")
    public String getExoplanet() throws JsonProcessingException {

        List<Exoplanet> exoplanetList = exoplanetService.getExoPlanetList();

        exoplanetService.getNumberOfOrphanPlanets(exoplanetList);

        exoplanetService.getNameOfPlanetOrbitingHottestStar(exoplanetList);

        exoplanetService.getTimelineOfPlanets(exoplanetList);

        return "Program executed successfully. Please look into the console for the output.";
    }


}
