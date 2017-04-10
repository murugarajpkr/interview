package com.interview.exam.solution;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by murugaraj on 4/10/2017.
 */
public class AirportBaggageImplTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findShortestPathForBagsRoute() throws Exception {

        try {
            AirportBaggage airportBaggage = new AirportBaggageImpl();
            airportBaggage.findShortestPathForBagsRoute();
        } catch (Exception exception_) {
            exception_.printStackTrace();
        }

    }

}