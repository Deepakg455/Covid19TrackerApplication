package com.corona.covid.service;

import com.corona.covid.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {


	//public static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	  public static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	//public static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	private List<LocationStats> locationStatsList = new ArrayList<LocationStats>();

	public List<LocationStats> getLocationStatsList() {
		return locationStatsList;
	}

	@PostConstruct
	@Scheduled(cron="10 * * * * *")
	public void fetchVirusData() throws IOException, InterruptedException {

		List<LocationStats> locationStatsListNew = new ArrayList<LocationStats>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_URL)).build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		//System.out.println("World   "+httpResponse.body());
		StringReader csvReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader); //Another format -RFC4180
		for (CSVRecord record : records) {
			LocationStats locationStats = new LocationStats();
			String state = record.get("Province/State");
			String country = record.get("Country/Region");
			int latestData = Integer.parseInt(record.get(record.size()-1));
			int previousData =Integer.parseInt(record.get(record.size()-2));
			int changeFromLastDay = latestData-previousData;
			locationStats.setState(state);
			locationStats.setCountry(country);
			locationStats.setLatestData(latestData);
			locationStats.setPreviousData(previousData);
			locationStats.setChangeFromLastDay(changeFromLastDay);
			//System.out.println("state/country"+locationStats);
			locationStatsListNew.add(locationStats);
			//System.out.println("state/country"+state+" ---- "+country+"--------"+latestData);
			//this.locationStatsList.clear();
			this.locationStatsList =locationStatsListNew;
		}
	}
}