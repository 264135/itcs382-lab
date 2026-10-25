Files read: bikeshare-poor/src/main/java/bikeshare/Main.java

| No. | Check | Code location (file:line) | Finding | Severity (Must fix, Should fix or Note) | Suggested fix |
| --- | --- | --- | --- | --- | --- |
| 1 | 1 | Main.java:42 | `process` is hard to read because renting and returning are two branches of one method. | Should fix | Split renting and returning into two methods. |
| 2 | 1 | Main.java:9 | The station names in the arrays are repeated in the validation at Main.java:43. | Should fix | Keep the three station names in one place. |
| 3 | 2 | Main.java:8 | `bikeIds`, `bikeStations`, `bikeMembers`, and `bikeTypes` are public. | Should fix | Make the fleet fields private. |
| 4 | 2 | Main.java:42 | `process` has logical cohesion: the boolean `returning` selects rent or return. | Should fix | Give rent and return their own methods. |
| 5 | 2 | Main.java:80 | `fee` has logical cohesion: the boolean `staff` selects which rate to use. | Should fix | Pass the member type and drop the flag. |
| 6 | 2 | Main.java:27 | Control coupling: `main` passes `false` or `true` to change what `process` does. | Should fix | Call a rent method or a return method. |
| 7 | 2 | Main.java:57 | Control coupling: `process` passes `type == 2` so that `fee` changes its behavior. | Should fix | Let `fee` use the member type directly. |
| 8 | 2 | Main.java:7 | `Main` has weak class cohesion. It owns the menu, the bike records, the fee rule, and the CSV file. | Should fix | Put the fleet, the fee, and the file in their own types. |
| 9 | 2 | Main.java:8 | Connascence of position: the four arrays describe one bike only when the same index is used in each. | Should fix | Store each bike in one object. |
| 10 | 2 | Main.java:61 | Connascence of convention: `1` means student and `2` means staff, and `fee` must use those same codes. | Should fix | Replace the codes with an enum. |
| 11 | 2 | Main.java:16 | `main` has logical cohesion: the loop chooses unrelated commands. | Note | Keep the menu, and move each command's work into its own method. |

Probable bugs such as an unclosed file, a string compared with `==`, an empty catch, or unused code: not found. The writer at Main.java:109 is closed, and both catch blocks report the failure.

Methods whose cohesion is procedural or weaker: `process` (logical, Main.java:42), `fee` (logical, Main.java:80), and `main` (logical, Main.java:16). `findMember`, `findFreeAt`, and `saveRide` are functional: not found at procedural or weaker.

Coupling between classes: not found, because the program has one class. Fields that return an internal collection: not found.

Check 1 findings: 2
Check 2 findings: 9
