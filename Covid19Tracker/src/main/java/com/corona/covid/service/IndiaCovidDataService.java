package com.corona.covid.service;

import com.corona.covid.model.CurrentData;
import com.corona.covid.model.IndiaStats;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class IndiaCovidDataService {

    public static String INDIA_DATA_URL = "https://raw.githubusercontent.com/covid19india/api/master/data.json";

    private List<IndiaStats> indiaStatsListGlobal = new ArrayList<IndiaStats>();

    public List<IndiaStats> getIndiaStatsList() {
        return indiaStatsListGlobal;
    }
    @PostConstruct
    @Scheduled(cron = "1 * * * * *")
    public void fetchIndiaStatsData() throws IOException, InterruptedException, ParseException, java.text.ParseException {
        List<IndiaStats> indiaStatsList = new ArrayList<IndiaStats>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(INDIA_DATA_URL)).build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println("fetchIndiaStatsData   "+httpResponse.body());

        Object obj = new JSONParser().parse(httpResponse.body());   // parsing
        JSONObject jsonObject = (JSONObject) obj;                // typecasting obj to JSONObject
       // System.out.println("JSON Object"+jsonObject.get("cases_time_series"));
        JSONArray statewise = (JSONArray) jsonObject.get("statewise");
        Iterator iterator = statewise.iterator();


        while(iterator.hasNext()) {
            IndiaStats indiaStats = new IndiaStats();
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            if(Integer.parseInt(jsonObject1.get("confirmed").toString())>0){

            CurrentData currentData = new CurrentData();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            indiaStats.setState((String) jsonObject1.get("state"));
            indiaStats.setConfirmCase(Integer.parseInt(jsonObject1.get("confirmed").toString()));
            indiaStats.setActiveCase(Integer.parseInt(jsonObject1.get("active").toString()));
            indiaStats.setRecoveredCase(Integer.parseInt(jsonObject1.get("recovered").toString()));
            indiaStats.setDeceasedCase(Integer.parseInt(jsonObject1.get("deaths").toString()));
            Date lastupdatedtime = (Date) dateFormat.parse(jsonObject1.get("lastupdatedtime").toString());
            indiaStats.setLastUpDatedTime(lastupdatedtime);
            JSONObject delta = (JSONObject) jsonObject1.get("delta");
            currentData.setActive(Integer.parseInt(delta.get("active").toString()));
            currentData.setConfirmed(Integer.parseInt(delta.get("confirmed").toString()));
            currentData.setDeath(Integer.parseInt(delta.get("deaths").toString()));
            currentData.setRecovered(Integer.parseInt(delta.get("recovered").toString()));
            indiaStats.setCurrentData(currentData);
            System.out.println("JSON Object  : " + indiaStats.getState() +"   "+indiaStats.getConfirmCase()+"   "+indiaStats.getLastUpDatedTime());
            indiaStatsList.add(indiaStats);
            }
        }

        this.indiaStatsListGlobal.clear();
        this.indiaStatsListGlobal.addAll(indiaStatsList);
        System.out.println(this.indiaStatsListGlobal);


    }

}
