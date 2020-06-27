package com.corona.covid.controller;

import com.corona.covid.model.IndiaStats;
import com.corona.covid.service.IndiaCovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndiaController {

    @Autowired
    IndiaCovidDataService indiaCovidDataService;

    @GetMapping("/")
    public String getFetchIndiaData(Model model){

        List<IndiaStats> indiaStatsList = indiaCovidDataService.getIndiaStatsList();
        // int totalActive =indiaStatsList.get(0).getConfirmCase();
        int totalActive = indiaStatsList.stream().filter(active->active.getState().equals("Total")).mapToInt(hello->hello.getActiveCase()).sum();
        int totalConfirm = indiaStatsList.stream().filter(active->active.getState().equals("Total")).mapToInt(confirm->confirm.getConfirmCase()).sum();
        int totalDeaths = indiaStatsList.stream().filter(active->active.getState().equals("Total")).mapToInt(death->death.getDeceasedCase()).sum();
        int totalRecover = indiaStatsList.stream().filter(active->active.getState().equals("Total")).mapToInt(recover->recover.getRecoveredCase()).sum();

        model.addAttribute("indiaStatsList",indiaStatsList);
        model.addAttribute("totalConfirm",totalConfirm);
        model.addAttribute("totalActive",totalActive);
        model.addAttribute("totalDeaths",totalDeaths);
        model.addAttribute("totalRecover",totalRecover);

        return "home";
    }
}
