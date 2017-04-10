package com.interview.exam.bean.airportBaggage;

import java.io.Serializable;
import java.util.List;

/**
 * Created by murugaraj on 4/8/2017.
 */
public class BagsRoutingInputDetailsBean implements Serializable {

    private List<ConveyorSystemBean> conveyorSystemBeanList;
    private List<DeparturesBean> departuresBeanList;
    private List<BagsBean> bagsBeanList;

    public List<ConveyorSystemBean> getConveyorSystemBeanList() {
        return conveyorSystemBeanList;
    }

    public void setConveyorSystemBeanList(List<ConveyorSystemBean> conveyorSystemBeanList) {
        this.conveyorSystemBeanList = conveyorSystemBeanList;
    }

    public List<DeparturesBean> getDeparturesBeanList() {
        return departuresBeanList;
    }

    public void setDeparturesBeanList(List<DeparturesBean> departuresBeanList) {
        this.departuresBeanList = departuresBeanList;
    }

    public List<BagsBean> getBagsBeanList() {
        return bagsBeanList;
    }

    public void setBagsBeanList(List<BagsBean> bagsBeanList) {
        this.bagsBeanList = bagsBeanList;
    }
}
