Files read: parcelpoint/src/main/java/parcelpoint/Locker.java, parcelpoint/src/main/java/parcelpoint/Main.java, parcelpoint/src/main/java/parcelpoint/Parcel.java, parcelpoint/src/main/java/parcelpoint/ParcelService.java

| No. | Check | Code location (file:line) | Finding | Severity (Must fix, Should fix or Note) | Suggested fix |
| --- | --- | --- | --- | --- | --- |
| 1 | 1 | ParcelService.java:42 | `parcel.getCode() == code` compares two String objects by identity, so a pickup of an existing code does not find the parcel. | Must fix | Compare the codes with `equals`. |
| 2 | 1 | ParcelService.java:76 | The `FileWriter` is never closed or flushed, so the bytes written to `parcels.csv` can be lost when the program ends. | Must fix | Open the writer in try-with-resources. |
| 3 | 1 | ParcelService.java:81 | The `catch` block is empty, so an `IOException` while saving is discarded. | Must fix | Report the exception instead of ignoring it. |
| 4 | 1 | ParcelService.java:6 | `java.util.Date` is imported and never used. | Should fix | Delete the unused import. |
| 5 | 1 | ParcelService.java:12 | The field `maxParcels` is assigned and never read. | Should fix | Delete the unused field. |
| 6 | 1 | Main.java:8 | The `Scanner` opened on `System.in` is never closed. | Should fix | Close it with try-with-resources. |
| 7 | 1 | ParcelService.java:49 | `pickUp` returns `0` both when the parcel is missing and when the stay is still free, so the caller cannot tell those cases apart. | Note | Return a distinct result when the code is not stored. |
| 8 | 2 | Parcel.java:7 | The field `status` is public, so any caller can change a parcel's state without a method of `Parcel`. | Should fix | Make `status` private and add a getter and a setter. |
| 9 | 2 | ParcelService.java:21 | `getLockers` returns the internal `ArrayList`, so a caller can add, remove, or replace lockers. | Should fix | Return an unmodifiable view, or do not expose the list. |
| 10 | 2 | ParcelService.java:64 | `printParcels` has logical cohesion: the boolean `forStaff` selects one of two unrelated printouts. | Should fix | Split the student listing and the staff listing into two methods. |
| 11 | 2 | Main.java:21 | Passing `true` into `printParcels` is control coupling, because the flag changes what the method does. | Should fix | Call a method whose name is the staff listing, with no flag. |
| 12 | 2 | ParcelService.java:9 | `ParcelService` has weak class cohesion. It owns lockers, parcel records, the fee rule, the SMS text, CSV saving, and both printouts. | Should fix | Move pricing and saving into their own types. |
| 13 | 2 | Locker.java:5 | Connascence of convention: locker size, parcel size, and `fee` all have to use the same int codes `1`, `2`, and `3`. The same codes are repeated at Parcel.java:6 and ParcelService.java:56. | Should fix | Replace the shared codes with one enum. |
| 14 | 2 | Main.java:21 | `Main` reaches through `ParcelService.getLockers()` to call `Locker.isFree`, so the menu depends on both types. | Should fix | Ask `ParcelService` for the number of free lockers. |
| 15 | 2 | ParcelService.java:52 | Stamp coupling: `fee` receives a `Parcel` but reads only `getSize()`. | Note | Pass the size only. |
| 16 | 2 | ParcelService.java:24 | Stamp coupling: `storeParcel` receives a whole `Parcel`. | Note | Pass only the fields the method uses. |
| 17 | 2 | Parcel.java:13 | Connascence of convention: `Parcel` and `ParcelService` share the status strings `"NEW"`, `"STORED"`, and `"PICKED_UP"` (ParcelService.java:28, ParcelService.java:44, and ParcelService.java:68). | Should fix | Give the status its own type so both classes share a name instead of a literal. |

Methods whose cohesion is procedural or weaker: `printParcels` (logical, ParcelService.java:64) and `Main.main` (logical, Main.java:10, because the loop chooses unrelated commands). Other methods are functional or sequential: not found at procedural or weaker.

Interactions tighter than data coupling that were found are listed above (control coupling at Main.java:21, stamp coupling at ParcelService.java:52 and ParcelService.java:24). Content coupling and common coupling: not found.

Check 1 findings: 7
Check 2 findings: 10
