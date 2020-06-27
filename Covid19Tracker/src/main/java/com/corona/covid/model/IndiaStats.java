package com.corona.covid.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IndiaStats {

    private String state;
    private int confirmCase;
    private int activeCase;
    private int recoveredCase;
    private int deceasedCase;
    private Date lastUpDatedTime;
    private CurrentData currentData;

    List<IndiaStats> indiaStatsList = new ArrayList<IndiaStats>();

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getConfirmCase() {
        return confirmCase;
    }

    public void setConfirmCase(int confirmCase) {
        this.confirmCase = confirmCase;
    }

    public int getActiveCase() {
        return activeCase;
    }

    public void setActiveCase(int activeCase) {
        this.activeCase = activeCase;
    }

    public int getRecoveredCase() {
        return recoveredCase;
    }

    public void setRecoveredCase(int recoveredCase) {
        this.recoveredCase = recoveredCase;
    }

    public int getDeceasedCase() {
        return deceasedCase;
    }

    public void setDeceasedCase(int deceasedCase) {
        this.deceasedCase = deceasedCase;
    }

    public Date getLastUpDatedTime() {
        return lastUpDatedTime;
    }

    public void setLastUpDatedTime(Date lastUpDatedTime) {
        this.lastUpDatedTime = lastUpDatedTime;
    }

    public CurrentData getCurrentData() {
        return currentData;
    }

    public void setCurrentData(CurrentData currentData) {
        this.currentData = currentData;
    }

    public List<IndiaStats> getIndiaStatsList() {
        return indiaStatsList;
    }

    public void setIndiaStatsList(List<IndiaStats> indiaStatsList) {
        this.indiaStatsList = indiaStatsList;
    }

    @Override
    public String toString() {
        return "IndiaStats{" +
                "state='" + state + '\'' +
                ", confirmCase=" + confirmCase +
                ", activeCase=" + activeCase +
                ", recoveredCase=" + recoveredCase +
                ", deceasedCase=" + deceasedCase +
                ", lastUpDatedTime=" + lastUpDatedTime +
                ", currentData=" + currentData +
                ", indiaStatsList=" + indiaStatsList +
                '}';
    }
}
