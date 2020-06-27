package com.corona.covid.model;

public class LocationStats {

    private String state;
    private String country;
    private int latestData;
    private int previousData;
    private int changeFromLastDay;

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

    public int getLatestData() {
        return latestData;
    }

    public void setLatestData(int latestData) {
        this.latestData = latestData;
    }

    public int getPreviousData() {
        return previousData;
    }

    public void setPreviousData(int previousData) {
        this.previousData = previousData;
    }

    public int getChangeFromLastDay() {return changeFromLastDay; }

    public void setChangeFromLastDay(int changeFromLastDay) {this.changeFromLastDay = changeFromLastDay;}

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestData=" + latestData +
                ", previousData=" + previousData +
                ", changeFromLastDay=" + changeFromLastDay +
                '}';
    }
}
