package bikeshare;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private final String name;
    private final List<Bike> bikes = new ArrayList<>();

    public Station(String name, String firstId, String secondId) {
        this.name = name;
        bikes.add(new Bike(firstId));
        bikes.add(new Bike(secondId));
    }

    public String getName() {
        return name;
    }

    public Bike freeBike() {
        for (Bike bike : bikes) {
            if (bike.isFree()) {
                return bike;
            }
        }
        return null;
    }

    public boolean hasMember(String memberId) {
        return bikeRentedBy(memberId) != null;
    }

    public Bike bikeRentedBy(String memberId) {
        for (Bike bike : bikes) {
            if (bike.isRentedBy(memberId)) {
                return bike;
            }
        }
        return null;
    }

    public Bike takeRentedBy(String memberId) {
        Bike bike = bikeRentedBy(memberId);
        if (bike != null) {
            bikes.remove(bike);
        }
        return bike;
    }

    public void park(Bike bike) {
        bikes.add(bike);
    }
}
