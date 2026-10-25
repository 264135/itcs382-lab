package bikeshare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BikeShare {
    private final List<Station> stations = new ArrayList<>();
    private final RideLog rideLog;

    public BikeShare(RideLog rideLog) {
        this.rideLog = rideLog;
        stations.add(new Station("Library", "L1", "L2"));
        stations.add(new Station("Canteen", "C1", "C2"));
        stations.add(new Station("Dorm", "D1", "D2"));
    }

    public String rent(String memberId, MemberType memberType, String stationName) {
        if (stationOf(memberId) != null) {
            return "Member already has a bike";
        }
        Station station = findStation(stationName);
        if (station == null) {
            return "Unknown station";
        }
        Bike bike = station.freeBike();
        if (bike == null) {
            return "No bike available";
        }
        bike.rentTo(memberId, memberType);
        return "Rented bike " + bike.getId() + " at " + stationName;
    }

    public Ride returnBike(String memberId, int minutes, String stationName) throws IOException {
        Station destination = findStation(stationName);
        if (destination == null) {
            throw new IllegalArgumentException("Unknown station");
        }
        Station current = stationOf(memberId);
        if (current == null) {
            throw new IllegalArgumentException("No rental for " + memberId);
        }
        Bike bike = current.takeRentedBy(memberId);
        MemberType type = bike.release();
        destination.park(bike);
        Ride ride = new Ride(bike.getId(), minutes, type.fee(minutes));
        rideLog.add(ride);
        return ride;
    }

    private Station findStation(String name) {
        for (Station station : stations) {
            if (station.getName().equals(name)) {
                return station;
            }
        }
        return null;
    }

    private Station stationOf(String memberId) {
        for (Station station : stations) {
            if (station.hasMember(memberId)) {
                return station;
            }
        }
        return null;
    }
}
