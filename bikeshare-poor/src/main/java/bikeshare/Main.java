package bikeshare;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String[] bikeIds = {"L1", "L2", "C1", "C2", "D1", "D2"};
    public static String[] bikeStations = {"Library", "Library", "Canteen", "Canteen", "Dorm", "Dorm"};
    public static String[] bikeMembers = new String[6];
    public static int[] bikeTypes = new int[6];

    public static void main(String[] args) {
        System.out.println("Commands: rent <memberId> <type 1 student or 2 staff> <station>, return <memberId> <minutes> <station>, quit");
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
                        process(false, words[1], Integer.parseInt(words[2]), words[3], 0);
                    } else if (words[0].equals("return")) {
                        process(true, words[1], 0, words[3], Integer.parseInt(words[2]));
                    } else {
                        System.out.println("Unknown command");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Type and minutes must be whole numbers");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Missing command argument");
                }
            }
        }
    }

    public static void process(boolean returning, String memberId, int memberType, String station, int minutes) {
        if (!station.equals("Library") && !station.equals("Canteen") && !station.equals("Dorm")) {
            System.out.println("Unknown station");
            return;
        }
        if (returning) {
            int index = findMember(memberId);
            if (index < 0) {
                System.out.println("No rental for " + memberId);
                return;
            }
            String bike = bikeIds[index];
            int type = bikeTypes[index];
            bikeMembers[index] = null;
            bikeStations[index] = station;
            int fee = fee(type, minutes, type == 2);
            System.out.println("Receipt: bike " + bike + ", minutes " + minutes + ", fee " + fee);
            saveRide(bike, minutes, fee);
        } else {
            if (memberType != 1 && memberType != 2) {
                System.out.println("Unknown member type");
                return;
            }
            if (findMember(memberId) >= 0) {
                System.out.println("Member already has a bike");
                return;
            }
            int index = findFreeAt(station);
            if (index < 0) {
                System.out.println("No bike available");
                return;
            }
            bikeMembers[index] = memberId;
            bikeTypes[index] = memberType;
            System.out.println("Rented bike " + bikeIds[index] + " at " + station);
        }
    }

    public static int fee(int memberType, int minutes, boolean staff) {
        if (staff) {
            return minutes * 3;
        }
        if (memberType == 1) {
            return minutes * 2;
        }
        return minutes * 3;
    }

    private static int findMember(String memberId) {
        for (int i = 0; i < bikeMembers.length; i++) {
            if (memberId.equals(bikeMembers[i])) {
                return i;
            }
        }
        return -1;
    }

    private static int findFreeAt(String station) {
        for (int i = 0; i < bikeIds.length; i++) {
            if (bikeMembers[i] == null && station.equals(bikeStations[i])) {
                return i;
            }
        }
        return -1;
    }

    private static void saveRide(String bike, int minutes, int fee) {
        try (FileWriter writer = new FileWriter("rides.csv", true)) {
            writer.write(bike + "," + minutes + "," + fee + "\n");
        } catch (IOException e) {
            System.out.println("Could not save ride: " + e.getMessage());
        }
    }
}
