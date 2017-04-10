package com.interview.exam.bean.airportBaggage;

import java.io.Serializable;

/**
 * Created by murugaraj on 4/8/2017.
 */
public class ConveyorSystemBean implements Serializable {

    private String node1;
    private String node2;
    private int travelTime;

    public String getNode1() {
        return node1;
    }

    public void setNode1(String node1) {
        this.node1 = node1;
    }

    public String getNode2() {
        return node2;
    }

    public void setNode2(String node2) {
        this.node2 = node2;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }
}
