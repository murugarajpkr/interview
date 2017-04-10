package com.interview.exam.bean.airportBaggage;

import com.interview.exam.solution.algorithm.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by murugaraj on 4/8/2017.
 */
public class BaggageRouteBean implements Serializable{

    private String bagNumber;
    private List<String> optimizedRouteList = new ArrayList<String>();
    private int totalTravelTime;

    public void addOptimizedRoute(String route) {
        optimizedRouteList.add(route);
    }

    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public List<String> getOptimizedRouteList() {
        return optimizedRouteList;
    }

    public void setOptimizedRouteList(List<String> optimizedRouteList) {
        this.optimizedRouteList = optimizedRouteList;
    }

    public int getTotalTravelTime() {
        return totalTravelTime;
    }

    public void setTotalTravelTime(int totalTravelTime) {
        this.totalTravelTime = totalTravelTime;
    }
}
