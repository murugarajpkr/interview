package com.interview.exam.bean.airportBaggage;

import java.io.Serializable;

/**
 * Created by murugaraj on 4/8/2017.
 */
public class DeparturesBean implements Serializable {

    private String flightId;
    private String flightGate;
    private String destination;
    private String flightTime;

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getFlightGate() {
        return flightGate;
    }

    public void setFlightGate(String flightGate) {
        this.flightGate = flightGate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }
}
