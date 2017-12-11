package bus.orm.service;

import bus.orm.entity.BusLocation;

public interface BusLocationDAO {

    boolean uploadLocation(BusLocation busLocation);

    boolean clearLocation(BusLocation busLocation);

    BusLocation downLocation(String busId);
}
