package com.interview.exam.bean.airportBaggage;

import java.io.Serializable;

/**
 * Created by murugaraj on 4/8/2017.
 */
public class BagsBean implements Serializable {

    private String bagNumber;
    private String entryPoint;
    private String flightId;

    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public String getEntryPoint() {
        return entryPoint;
    }

    public void setEntryPoint(String entryPoint) {
        this.entryPoint = entryPoint;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
}
