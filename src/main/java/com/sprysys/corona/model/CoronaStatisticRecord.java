package com.sprysys.corona.model;

public class CoronaStatisticRecord {
	private String state;
	private String country;
	private int todaysCases;
	private int yesterdayCases;

	public int getTodaysCases() {
		return todaysCases;
	}

	public void setTodaysCases(int todaysCases) {
		this.todaysCases = todaysCases;
	}

	public int getYesterdayCases() {
		return yesterdayCases;
	}

	public void setYesterdayCases(int yesterdayCases) {
		this.yesterdayCases = yesterdayCases;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "StatRecord [state=" + state + ", country=" + country + ", todaysCases=" + todaysCases
				+ ", yesterdayCases=" + yesterdayCases + "]";
	}

}
