package com.corona.covid.controller;

import com.corona.covid.model.LocationStats;
import com.corona.covid.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GlobalController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;
    @GetMapping("/global")
    public String globalData(Model model)
    {
        List<LocationStats> locationStatsList = coronaVirusDataService.getLocationStatsList();
        int totalCase = locationStatsList.stream().mapToInt(stat->stat.getLatestData()).sum();
        int totalNewCase = locationStatsList.stream().mapToInt(stat->stat.getChangeFromLastDay()).sum();

        model.addAttribute("coronaData",locationStatsList);
        model.addAttribute("totalCase",totalCase);
        model.addAttribute("totalNewCase",totalNewCase);
        return "global";
    }

    @GetMapping("/link")
    public String getLinks(){
        return "helpfulLink";
    }
}
