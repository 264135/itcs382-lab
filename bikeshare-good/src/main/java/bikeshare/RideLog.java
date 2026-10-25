package bikeshare;

import java.io.IOException;

public interface RideLog {
    void add(Ride ride) throws IOException;
}
