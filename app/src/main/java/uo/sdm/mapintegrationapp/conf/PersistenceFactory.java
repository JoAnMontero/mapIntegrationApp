package uo.sdm.mapintegrationapp.conf;

import uo.sdm.mapintegrationapp.persistence.ZoneGateway;
import uo.sdm.mapintegrationapp.persistence.impl.ZoneGatewayImpl;

public class PersistenceFactory {
    public static ZoneGateway getZoneGateway() {
        return new ZoneGatewayImpl();
    }
}
