Files read: bikeshare-good/src/main/java/bikeshare/Bike.java, bikeshare-good/src/main/java/bikeshare/BikeShare.java, bikeshare-good/src/main/java/bikeshare/CsvRideLog.java, bikeshare-good/src/main/java/bikeshare/Main.java, bikeshare-good/src/main/java/bikeshare/MemberType.java, bikeshare-good/src/main/java/bikeshare/Ride.java, bikeshare-good/src/main/java/bikeshare/RideLog.java, bikeshare-good/src/main/java/bikeshare/Station.java

| No. | Check | Code location (file:line) | Finding | Severity (Must fix, Should fix or Note) | Suggested fix |
| --- | --- | --- | --- | --- | --- |
| 1 | 1 | Main.java:33 | `catch (Exception e)` handles a missing argument and a file error in the same place, so the failure is harder to see. | Note | Catch `ArrayIndexOutOfBoundsException` and `IOException` separately. |
| 2 | 2 | Main.java:10 | `main` has logical cohesion: the loop chooses unrelated commands. | Note | Leave the menu here, since each command already calls one method. |
| 3 | 2 | BikeShare.java:6 | `BikeShare` depends on `Station`, `Bike`, `MemberType`, `Ride`, and `RideLog`. | Note | Keep it as the coordinator, and do not add more jobs to it. |
| 4 | 2 | Main.java:6 | The menu constructs `CsvRideLog`, so `Main` names the CSV class as well as the `RideLog` interface. | Note | Pass a `RideLog` into `main` from the startup code. |
| 5 | 2 | BikeShare.java:13 | Connascence of convention: a caller must type `Library`, `Canteen`, or `Dorm` exactly as these constructors do. | Should fix | Give the stations an enum and parse the typed name once. |

Probable bugs such as a resource left open, a string compared with `==`, an empty block, unused code, or a method that is too long: not found. `CsvRideLog.add` closes its writer, and every field is private.

Methods whose cohesion is procedural or weaker: `main` (logical, Main.java:10). Other methods are functional or sequential: not found at procedural or weaker.

Control coupling, common coupling, and content coupling: not found. No method returns a class's internal collection.

Check 1 findings: 1
Check 2 findings: 4
