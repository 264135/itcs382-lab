# Lab Report, Code Quality and Design Quality

Repository URL: https://github.com/264135/itcs382-lab

## Tools

| Item | Version or name |
| --- | --- |
| JDK | 25.0.5 Temurin (from `java -version`). Maven runs on 27.0.1.0.3. |
| Maven | 3.10.0 |
| IDE | Visual Studio Code 1.141.2 |
| SonarQube for IDE | 6.2.0 |
| AI tool and exact model | Cursor, Grok 4.7 |

## Task 1. SonarQube on the starter code

Commit of the starter, before any change: 30c0074

Table 1. SonarQube issues in the starter code

| No. | Rule | File:line | Message |
| --- | --- | --- | --- |
| 1 | java:S106 | Main.java:9 | Replace this use of System.out by a logger. |
| 2 | java:S106 | Main.java:15 | Replace this use of System.out by a logger. |
| 3 | java:S106 | Main.java:17 | Replace this use of System.out by a logger. |
| 4 | java:S106 | Main.java:22 | Replace this use of System.out by a logger. |
| 5 | java:S2143 | ParcelService.java:1 | Use the "java.time" API for date and time. |
| 6 | java:S1128 | ParcelService.java:6 | Remove this unused import 'java.util.Date'. |
| 7 | java:S1068 | ParcelService.java:12 | Remove this unused "maxParcels" private field. |
| 8 | java:S106 | ParcelService.java:30 | Replace this use of System.out by a logger. |
| 9 | java:S4973 | ParcelService.java:42 | Strings and Boxed types should be compared using "equals()". |
| 10 | java:S106 | ParcelService.java:67 | Replace this use of System.out by a logger. |
| 11 | java:S106 | ParcelService.java:69 | Replace this use of System.out by a logger. |
| 12 | java:S2095 | ParcelService.java:76 | Use try-with-resources or close this "FileWriter" in a "finally" clause. |
| 13 | java:S108 | ParcelService.java:80 | Remove this block of code, fill it in, or add a comment explaining why it is empty. |

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
| 1 | ParcelService.java:42 | Pickup compares parcel codes with `==`. | both | java:S4973 | yes, because the two codes are different String objects, and the starter run printed Fee: 0.0 and left the parcel STORED |
| 2 | ParcelService.java:76 | The FileWriter is never closed or flushed. | both | java:S2095 | yes, because saveToFile creates the writer and the starter run left parcels.csv empty |
| 3 | ParcelService.java:80 | The catch block is empty. | both | java:S108 | yes, because the catch body has no statement |
| 4 | ParcelService.java:6 | The Date import is unused. | both | java:S1128 | yes, because Date does not appear again in the file |
| 5 | ParcelService.java:12 | maxParcels is never read. | both | java:S1068 | yes, because the field is only assigned 100 |
| 6 | Main.java:8 | The Scanner is never closed. | AI | none | yes, because main never closes the Scanner, and SonarQube did not report it |
| 7 | ParcelService.java:49 | pickUp returns 0 both when the parcel is missing and when the stay is free. | AI | none | yes, because the missing-code path and the days <= 3 path both return 0, and SonarQube did not report it |
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
| 18 | ParcelService.java:1 | Use the "java.time" API for date and time. | SonarQube | java:S2143 | yes, because the file imports java.util.Date |
| 19 | Main.java:9 | Replace this use of System.out by a logger. | SonarQube | java:S106 | yes, because this line prints with System.out |
| 20 | Main.java:15 | Replace this use of System.out by a logger. | SonarQube | java:S106 | yes, because this line prints with System.out |
| 21 | Main.java:17 | Replace this use of System.out by a logger. | SonarQube | java:S106 | yes, because this line prints with System.out |
| 22 | Main.java:22 | Replace this use of System.out by a logger. | SonarQube | java:S106 | yes, because this line prints with System.out |
| 23 | ParcelService.java:30 | Replace this use of System.out by a logger. | SonarQube | java:S106 | yes, because this line prints the SMS with System.out |
| 24 | ParcelService.java:67 | Replace this use of System.out by a logger. | SonarQube | java:S106 | yes, because this line prints with System.out |
| 25 | ParcelService.java:69 | Replace this use of System.out by a logger. | SonarQube | java:S106 | yes, because this line prints with System.out |

2.1 Name two problems that only the AI review found.

The public status field at Parcel.java:7 and getLockers returning the mutable internal list at ParcelService.java:21. Both are encapsulation problems. SonarQube did not report them. Its starter findings are the 13 rules in Table 1, and none of those rules names encapsulation, cohesion, coupling, or connascence.

2.2 Name one problem that only SonarQube found, or write none.

java:S106, "Replace this use of System.out by a logger", for example at Main.java:9. The AI review did not report the System.out calls.

2.3 Name one AI finding that was wrong or too vague, and explain why. If every finding was right, explain how you checked them.

Finding 16 is too vague. It says storeParcel should take separate fields because receiving a Parcel is stamp coupling. In the starter, storeParcel stores that object in the locker, changes its status, and reads its code, student id, and size. Passing the Parcel is the data that operation needs. The other findings were checked against the file and line in commit 30c0074, and findings 1 and 2 were also checked by running the starter.

## Task 3. Fix and check

Table 3. What you did with each SonarQube issue in Table 1

| No. in Table 1 | Fixed or kept | Commit, or the reason for keeping it |
| --- | --- | --- |
| 1 | Kept | Kept because this is intentional output from the command-line program. |
| 2 | Kept | Kept because this is intentional output from the command-line program. |
| 3 | Kept | Kept because this is intentional output from the command-line program. |
| 4 | Kept | Kept because this is intentional output from the command-line program. |
| 5 | Kept | Not fixed. java:S2143 is still reported at ParcelService.java:1. |
| 6 | Kept | Not fixed. java:S1128 is still reported at ParcelService.java:6. |
| 7 | Kept | Not fixed. java:S1068 is still reported at ParcelService.java:12. |
| 8 | Kept | Kept because this is intentional output from the command-line program. |
| 9 | Fixed | ef15e23 |
| 10 | Kept | Kept because this is intentional output from the command-line program. |
| 11 | Kept | Kept because this is intentional output from the command-line program. |
| 12 | Fixed | f7101b8 |
| 13 | Fixed | f7101b8 |

Number of SonarQube issues that remain after your fixes: 11

Three starter issues were fixed: java:S4973, java:S2095, and java:S108. Ten of the original 13 findings remain. Reporting the save error added a new java:S106 at ParcelService.java:80, so the current total is 11. By rule on the current code: java:S106 = 8, java:S2143 = 1, java:S1128 = 1, and java:S1068 = 1.

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
| SonarQube issues, total | 19 | 0 |
| SonarQube issues, by rule | java:S106 = 12; java:S2386 = 4; java:S1192 = 2; java:S135 = 1 | none |
| AI review findings in Check 1 | 2 | 1 |
| AI review findings in Check 2 | 9 | 4 |
| Review output file in the reviews folder | reviews/bikeshare-poor-review.md | reviews/bikeshare-good-review.md |

4.1 Did SonarQube report any cohesion or coupling problem in bikeshare-poor? Quote the rule and the message if it did.

No. SonarQube did not report a cohesion or coupling rule in bikeshare-poor. It reported 19 code-quality findings: java:S2386 ("Make this member \"protected\"."), java:S1192 (duplicated literals, including "Define a constant instead of duplicating this literal \"Canteen\" 3 times." and the same message for "Library"), java:S135 ("Reduce the total number of break and continue statements in this loop to use at most one."), and java:S106 ("Replace this use of System.out by a logger."). None of these verified findings explicitly identified cohesion or coupling.

4.2 Did the AI review tell the two versions apart? Give one finding for bikeshare-poor that does not appear for bikeshare-good.

Yes. The poor review reports connascence of position at Main.java:8, where four public arrays describe one bike only when they share an index, and connascence of convention at Main.java:61, where 1 means student and 2 means staff. The good review does not report either finding. Its bikes are private objects in a Station, and the member type is the MemberType enum.

4.3 Which version would you rather extend with a new member type, such as a visitor who pays 4 baht per minute, and why?

The good version. A visitor is one new MemberType constant whose rate is 4, and fee already multiplies that rate by the minutes. The poor version stores the member type as an int and passes a boolean into fee, so a third type fits neither the flag nor the 1-and-2 convention without rewriting fee and the command codes.

## Task 5. Reflection

5.1 What does SonarQube check, and what does it not check? Answer with examples from this lab.

SonarQube checks concrete static code quality. On the starter it reported the string comparison with == (java:S4973), the unclosed FileWriter (java:S2095), the empty catch (java:S108), the unused Date import, the unused maxParcels field, and repeated use of System.out (java:S106). It did not explicitly identify the cohesion, coupling, and connascence differences between bikeshare-poor and bikeshare-good. bikeshare-poor had 19 code-quality findings and bikeshare-good had none, and none of those rules named cohesion, coupling, or connascence. The AI review did identify those design-level differences, including the public parallel arrays and the int member-type codes in the poor version.

5.2 When should you not trust the AI review? Give an example from this lab.

Do not trust a finding until the named file and line show that problem. Finding 16 said storeParcel should take separate fields because a Parcel argument is stamp coupling. Reading ParcelService.storeParcel shows that it stores the object, changes its status, and reads its code, student id, and size, so the finding is too vague to act on. A line reference alone is not evidence.

5.3 What will you do differently in Project Phase 2 after this lab?

Use an automated code-quality check and a separate design review for cohesion, coupling, and connascence on the same commit. Check every AI finding against the source before changing code, as with the storeParcel finding that was not a real defect. Keep each fix small and record its commit, so a later reader can see which change addressed which problem.

## AI usage declaration

During this lab I used AI mainly as a support tool while working through the different steps. I used it when I was unsure about the workflow, to get explanations of concepts such as cohesion, coupling, connascence and code quality, and to help me understand some of the findings from SonarQube. I also used AI to discuss possible ways of improving the code and to help structure parts of the written report. For the required AI review tasks, I used the prompts provided in the assignment and then checked the findings against the actual Java files and the SonarQube results before including them in the report.

| AI tool and exact model | Purpose | Files or parts that it produced or changed | How you checked its output |
| --- | --- | --- | --- |
| ChatGPT, GPT-5.6 Sol | Used for guidance through the lab, explanations of course concepts, understanding SonarQube findings, discussing possible fixes and helping structure and phrase parts of the report. | Guidance, explanations and suggestions used while working through the lab, and assistance with wording parts of the report. | I compared the suggestions with the assignment instructions, the Java source code, the program output and the SonarQube results before using them. |
| Cursor, Grok 4.7 | Used for the AI review tasks required by the lab and for assistance while editing and testing the project. | AI review outputs in the reviews folder and suggested code/design changes. | I checked the findings against the relevant files and line numbers, rebuilt and ran the programs after changes, and compared the results with SonarQube. |
