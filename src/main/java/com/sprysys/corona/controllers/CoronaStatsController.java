package com.sprysys.corona.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sprysys.corona.model.CoronaStatisticRecord;
import com.sprysys.corona.services.CoronaStatsPullService;

@Controller
public class CoronaStatsController {

	@Autowired
	CoronaStatsPullService statsService;

	@GetMapping("/")
	public String getCoronaStats(Model model) throws ClientProtocolException, IOException {
		List<CoronaStatisticRecord> stats = statsService.fetchCoronaStats();
		model.addAttribute("allstats", stats);
		
		int todaysCount = stats.stream().mapToInt(statrec -> statrec.getTodaysCases()).sum();
		int yesterdaysCount = stats.stream().mapToInt(statrec -> statrec.getTodaysCases() - statrec.getYesterdayCases()).sum();
		
		model.addAttribute("todaysCount", todaysCount);
		model.addAttribute("yesterdaysCount", yesterdaysCount);
        
		return "stats";
	}
}
