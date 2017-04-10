package com.interview.exam.solution;

import com.interview.exam.bean.airportBaggage.*;

import java.util.List;

/**
 * Created by murugaraj on 4/8/2017.
 */
public interface AirportBaggage {

    public BagsRoutingInputDetailsBean getBagsRoutingInputDetails() throws Exception;

    public List<BaggageRouteBean> optimizedRouteOfBaggage(BagsRoutingInputDetailsBean bagsRoutingInputDetailsBean) throws Exception;

    /**
     *
     * @throws Exception
     */
    public void findShortestPathForBagsRoute() throws Exception;

}
