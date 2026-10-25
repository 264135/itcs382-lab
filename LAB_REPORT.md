# Lab Report, Code Quality and Design Quality

Repository URL: https://github.com/264135/itcs382-lab

## Tools

| Item | Version or name |
| --- | --- |
| JDK | 25.0.5 Temurin (from `java -version`). Maven runs on 27.0.1.0.3. |
| Maven | 3.10.0 |
| IDE | TODO_VERSION |
| SonarQube for IDE | TODO_VERSION |
| AI tool and exact model | Cursor, Grok 4.7 |

## Task 1. SonarQube on the starter code

Commit of the starter, before any change: 30c0074

Table 1. SonarQube issues in the starter code

| No. | Rule | File:line | Message |
| --- | --- | --- | --- |
| 1 | TODO_SONAR | TODO_SONAR | TODO_SONAR |

SonarQube for IDE was not installed in this environment, so Table 1 does not list any issue. No SonarQube finding was invented.

## Task 2. AI review of the starter code

| Item | Value |
| --- | --- |
| Date | 25 October 2026 |
| AI tool and exact model | Cursor, Grok 4.7 |
| Commit reviewed | 30c0074 |
| How the AI read the files (attached the files, opened the folder, or pasted the files) | opened the folder |
| Output file in the reviews folder | reviews/parcelpoint-review.md |

Table 2. All findings on the starter code

| No. | File:line | Finding | Found by (SonarQube, AI or both) | SonarQube rule or course term | Correct? (yes or no, and why) |
| --- | --- | --- | --- | --- | --- |
| 1 | ParcelService.java:42 | Pickup compares parcel codes with `==`. | AI | TODO_SONAR | yes, because the two codes are different String objects, and the starter run printed Fee: 0.0 and left the parcel STORED |
| 2 | ParcelService.java:76 | The FileWriter is never closed or flushed. | AI | TODO_SONAR | yes, because saveToFile creates the writer and the starter run left parcels.csv empty |
| 3 | ParcelService.java:81 | The catch block is empty. | AI | TODO_SONAR | yes, because the catch body has no statement |
| 4 | ParcelService.java:6 | The Date import is unused. | AI | TODO_SONAR | yes, because Date does not appear again in the file |
| 5 | ParcelService.java:12 | maxParcels is never read. | AI | TODO_SONAR | yes, because the field is only assigned 100 |
| 6 | Main.java:8 | The Scanner is never closed. | AI | TODO_SONAR | yes, because main never closes the Scanner |
| 7 | ParcelService.java:49 | pickUp returns 0 both when the parcel is missing and when the stay is free. | AI | TODO_SONAR | yes, because the missing-code path and the days <= 3 path both return 0 |
| 8 | Parcel.java:7 | status is public. | AI | encapsulation | yes, because the field is declared public and ParcelService assigns it directly |
| 9 | ParcelService.java:21 | getLockers returns the internal list. | AI | encapsulation | yes, because the method returns the same ArrayList the service mutates |
| 10 | ParcelService.java:64 | printParcels has logical cohesion because a boolean selects the printout. | AI | cohesion | yes, because the forStaff branch prints a different line from the other branch |
| 11 | Main.java:21 | Passing true into printParcels is control coupling. | AI | coupling | yes, because the boolean changes which lines printParcels prints |
| 12 | ParcelService.java:9 | ParcelService owns lockers, fees, SMS, saving, and printing. | AI | cohesion | yes, because those jobs are methods of this one class |
| 13 | Locker.java:5 | Size codes 1, 2, and 3 are shared with Parcel and fee. | AI | connascence | yes, because Locker, Parcel, and fee must use the same integers |
| 14 | Main.java:21 | Main reaches through getLockers to call Locker.isFree. | AI | coupling | yes, because the staff command filters the service's lockers itself |
| 15 | ParcelService.java:52 | fee receives a Parcel but reads only its size. | AI | coupling | yes, because the method body calls getSize and no other parcel method |
| 16 | ParcelService.java:24 | storeParcel receives a whole Parcel, called stamp coupling. | AI | coupling | no, this is too vague. storeParcel keeps the Parcel, sets its status, and reads its code, student id, and size, so the object is the data the method needs |
| 17 | Parcel.java:13 | Status strings are a shared convention with ParcelService. | AI | connascence | yes, because NEW, STORED, and PICKED_UP are written in one class and compared in the other |

2.1 Name two problems that only the AI review found.

TODO_SONAR. SonarQube for IDE results were not available, so this comparison was not made.

2.2 Name one problem that only SonarQube found, or write none.

TODO_SONAR. SonarQube for IDE results were not available, so no SonarQube-only problem can be named.

2.3 Name one AI finding that was wrong or too vague, and explain why. If every finding was right, explain how you checked them.

Finding 16 is too vague. It says storeParcel should take separate fields because receiving a Parcel is stamp coupling. In the starter, storeParcel stores that object in the locker, changes its status, and reads its code, student id, and size. Passing the Parcel is the data that operation needs. The other findings were checked against the file and line in commit 30c0074, and findings 1 and 2 were also checked by running the starter.

## Task 3. Fix and check

Table 3. What you did with each SonarQube issue in Table 1

| No. in Table 1 | Fixed or kept | Commit, or the reason for keeping it |
| --- | --- | --- |
| 1 | TODO_SONAR | TODO_SONAR |

Number of SonarQube issues that remain after your fixes: TODO_SONAR

The two design problems that you fixed

| Design problem (course term) | What you changed | Commit |
| --- | --- | --- |
| Encapsulation | Made Parcel.status private and added getStatus and setStatus. ParcelService now uses those methods. | ad2735c |
| Connascence of convention | Replaced the shared size codes 1, 2, and 3 with the ParcelSize enum, used by Locker, Parcel, and ParcelService. | 833b295 |

Output of the program after your fixes, for the commands `store P1 6501 2`, `pickup P1 5` and `staff`, and the content of parcels.csv:

```text
Commands: store <code> <studentId> <size 1-3>, pickup <code> <days>, list, staff, quit
SMS to 6501: parcel P1 is in locker 2
Locker 2
Fee: 30.0
P1 6501 PICKED_UP
Free lockers: 3

parcels.csv:
P1,PICKED_UP
```

## Task 4. AI-generated poor and good designs

| Item | bikeshare-poor | bikeshare-good |
| --- | --- | --- |
| Date | 25 October 2026 | 25 October 2026 |
| AI tool and exact model | Cursor, Grok 4.7 | Cursor, Grok 4.7 |
| Prompt (B or C as given, or your changed prompt in full) | B as given | C as given |
| Builds with mvn package (yes or no) | yes | yes |
| Number of classes, interfaces, enums and records | 1 class, 0 interfaces, 0 enums, 0 records | 6 classes, 1 interface, 1 enum, 0 records |
| SonarQube issues, total | TODO_SONAR | TODO_SONAR |
| SonarQube issues, by rule | TODO_SONAR | TODO_SONAR |
| AI review findings in Check 1 | 2 | 1 |
| AI review findings in Check 2 | 9 | 4 |
| Review output file in the reviews folder | reviews/bikeshare-poor-review.md | reviews/bikeshare-good-review.md |

4.1 Did SonarQube report any cohesion or coupling problem in bikeshare-poor? Quote the rule and the message if it did.

TODO_SONAR. SonarQube for IDE was not run, so this answer cannot quote a rule or a message.

4.2 Did the AI review tell the two versions apart? Give one finding for bikeshare-poor that does not appear for bikeshare-good.

Yes. The poor review reports connascence of position at Main.java:8, where four public arrays describe one bike only when they share an index, and connascence of convention at Main.java:61, where 1 means student and 2 means staff. The good review does not report either finding. Its bikes are private objects in a Station, and the member type is the MemberType enum.

4.3 Which version would you rather extend with a new member type, such as a visitor who pays 4 baht per minute, and why?

The good version. A visitor is one new MemberType constant whose rate is 4, and fee already multiplies that rate by the minutes. The poor version stores the member type as an int and passes a boolean into fee, so a third type fits neither the flag nor the 1-and-2 convention without rewriting fee and the command codes.

## Task 5. Reflection

5.1 What does SonarQube check, and what does it not check? Answer with examples from this lab.

TODO_SONAR for the SonarQube half of this answer: SonarQube for IDE was not run, so this lab has no SonarQube message to quote. The AI review did separate local code defects from design defects in the starter. It pointed at the `==` comparison in ParcelService.java:42, the unclosed FileWriter, and the empty catch, which are code-quality defects. The same review also named design problems, including the public status field, the duplicated size codes, and the boolean that gives printParcels logical cohesion.

5.2 When should you not trust the AI review? Give an example from this lab.

Do not trust a finding until the named file and line show that problem. Finding 16 said storeParcel should take separate fields because a Parcel argument is stamp coupling. Reading ParcelService.storeParcel shows that it stores the object, changes its status, and reads its code, student id, and size, so the finding is too vague to act on. A line reference alone is not evidence.

5.3 What will you do differently in Project Phase 2 after this lab?

Use an automated code-quality check and a separate design review for cohesion, coupling, and connascence on the same commit. Check every AI finding against the source before changing code, as with the storeParcel finding that was not a real defect. Keep each fix small and record its commit, so a later reader can see which change addressed which problem.

## AI usage declaration

| AI tool and exact model | Purpose | Files or parts that it produced or changed | How you checked its output |
| --- | --- | --- | --- |
| Cursor, Grok 4.7 | Prompt A review of the starter parcelpoint code on 25 October 2026 | reviews/parcelpoint-review.md and the Task 2 rows taken from it | Compared each finding with the Java file and line in commit 30c0074, and ran the starter for the pickup and file findings. No separate human review. |
| Cursor, Grok 4.7 | Prompt B, the poor BikeShare program, on 25 October 2026 | bikeshare-poor/pom.xml and bikeshare-poor/src/main/java/bikeshare/Main.java | mvn package reported BUILD SUCCESS. A student rental and return, a staff rental and return, and quit were run. No separate human review. |
| Cursor, Grok 4.7 | Prompt C, the good BikeShare program, on 25 October 2026 | bikeshare-good/pom.xml and the Java files under bikeshare-good/src/main/java/bikeshare | mvn package reported BUILD SUCCESS, and the same rental, return, and quit commands were run. No compile correction was required. No separate human review. |
| Cursor, Grok 4.7 | Prompt A reviews of both BikeShare versions on 25 October 2026 | reviews/bikeshare-poor-review.md and reviews/bikeshare-good-review.md | Compared each finding with the generated source and counted the Check 1 and Check 2 rows from those files. No separate human review. |
| Cursor, Grok 4.7 | The parcelpoint fixes and the wording of this report on 25 October 2026 | ParcelService.java, Parcel.java, Locker.java, Main.java, ParcelSize.java, and LAB_REPORT.md | mvn package succeeded after each fix, and the final command script produced the output copied in Task 3. No separate human review. |
