package bikeshare;

import java.io.FileWriter;
import java.io.IOException;

public class CsvRideLog implements RideLog {
    private final String path;

    public CsvRideLog(String path) {
        this.path = path;
    }

    @Override
    public void add(Ride ride) throws IOException {
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(ride.getBikeId() + "," + ride.getMinutes() + "," + ride.getFee() + "\n");
        }
    }
}
