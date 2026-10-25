package bikeshare;

public class Ride {
    private final String bikeId;
    private final int minutes;
    private final int fee;

    public Ride(String bikeId, int minutes, int fee) {
        this.bikeId = bikeId;
        this.minutes = minutes;
        this.fee = fee;
    }

    public String getBikeId() {
        return bikeId;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getFee() {
        return fee;
    }
}
