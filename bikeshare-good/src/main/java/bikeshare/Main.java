package bikeshare;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BikeShare share = new BikeShare(new CsvRideLog("rides.csv"));
        System.out.println("Commands: rent <memberId> <student or staff> <station>, return <memberId> <minutes> <station>, quit");
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNextLine()) {
                String line = in.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] words = line.split(" ");
                if (words[0].equals("quit")) {
                    break;
                }
                try {
                    if (words[0].equals("rent")) {
                        System.out.println(share.rent(words[1], MemberType.parse(words[2]), words[3]));
                    } else if (words[0].equals("return")) {
                        Ride ride = share.returnBike(words[1], Integer.parseInt(words[2]), words[3]);
                        System.out.println("Receipt: bike " + ride.getBikeId()
                                + ", minutes " + ride.getMinutes() + ", fee " + ride.getFee());
                    } else {
                        System.out.println("Unknown command");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Minutes must be a whole number");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Could not complete the command: " + e.getMessage());
                }
            }
        }
    }
}
