package com.sprysys.corona.services;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.sprysys.corona.model.CoronaStatisticRecord;

@Service
public class CoronaStatsPullService {
	private static final String CORONA_STATS_DATASOURCE = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	private static final int PROVINCE_STATE_INDEX = 0;
	private static final int COUNTRY_REGION_INDEX = 1;

	private List<CoronaStatisticRecord> allStatsList = new ArrayList<CoronaStatisticRecord>();

	@PostConstruct
	public List<CoronaStatisticRecord> fetchCoronaStats() throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClients.createDefault();
		HttpResponse response = httpClient.execute(new HttpGet(CORONA_STATS_DATASOURCE));
		HttpEntity entity = response.getEntity();
		String wholeCSV = entity != null ? EntityUtils.toString(entity) : "";

		StringReader reader = new StringReader(wholeCSV);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

		List<CoronaStatisticRecord> tempList = new ArrayList<>();

		for (CSVRecord record : records) {
			CoronaStatisticRecord coronaStatRec = new CoronaStatisticRecord();
			coronaStatRec.setState(record.get(PROVINCE_STATE_INDEX));
			coronaStatRec.setCountry(record.get(COUNTRY_REGION_INDEX));
			coronaStatRec.setTodaysCases(Integer.parseInt(record.get(record.size() - 1)));
			coronaStatRec.setYesterdayCases(Integer.parseInt(record.get(record.size() - 2)));

			tempList.add(coronaStatRec);
			System.out.println(coronaStatRec);
		}
		allStatsList = tempList;
		return allStatsList;
	}
}
