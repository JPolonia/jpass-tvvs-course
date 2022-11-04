# Assignment #4 - TVVS

- Filipe Cerqueira - 202204408
- João Polónia - 201208144

## Black Box Testing - Model-based Software Testing

For this assignment we started by experimenting with JPass application to get a grasp on several use cases the end user would experience. We uncover all it's basic functionality and then chose the following 3 use cases because they better demonstrate the State-based Testing method:

## **Use Case:** Add new Entry

This use case is adding a new entry. The user saves the login username and password and stores it in a list of "profiles"/entrys. This also can generate new passwords and gives the ability to the user to copy the saved password.

### UML State Diagram
![](https://i.imgur.com/iF1aI6k.png)


### Transition Tree: 7 test cases
![](https://i.imgur.com/AjfJfam.png)


### Sneak Path Testing: Transition table
All empty cells are sneaky transitions, within our tests we confirmed the aplication did the right job in blocking them from happening. We tried to break it but the results were always as expected.  

| **States/Events**          | **Click Add Entry** | **ok [title not empty]** | **ok [title empty]** | **cancel**      | **accept[NOT empty]** | **click generate password** | **accept [password empty]** | **ok[title NOT duplicate or empty]** |
|----------------------------|---------------------|--------------------------|----------------------|-----------------|-----------------------|-----------------------------|-----------------------------|--------------------------------------|
| **Idle**                   | Entry Form Menu     | -                        | -                    | -               | -                     | -                           | -                           | -                                    |
| **Entry Form Menu**        | -                   | Idle                     | Entry Form Menu      | Idle            | -                     | Generate Password Menu      | -                           | -                                    |
| **Generate Password Menu** | -                   | -                        | -                    | Entry Form Menu | Entry Form Menu       | -                           | Generate Password Menu      | Idle                                 |


### QF-Test Tool Screenshot
![](https://i.imgur.com/bObnMo2.png)


### Description of the outcome
1. Test-case was CancelCreateNewEntry, the goal was to open the entry form and then cancel it so we could go back to the home page.
2. Test-case was GeneratePasswordSuccess, which consisted in generating a password with the tool that the jpass had and create a new entry.
3. Test-case was GeneratePasswordFailure, it was very similar to the last test, but with the goal of try adding a new password generated without clicking on generate button. It gave the allert notification as expected.
4. The last test-case was trying add new entry with empty fields, it lead us to a allert notification and didn't let us create the entry.

The test-set passed, all the paths described in the trasition tree were complete. After each unique test case path we created a sequence so the next path could be tested.

State machine test adequacy:
- State coverage: 100%
- Transition coverage: 100%
- Path coverage: 100%

### QF Tool: Sneak Path
All sneak path that we could develop would result in a failure because the aplication blocks all the buttons or text fields that would lead us to that. The way it was developed in this test case all existing sneak paths are protected.


## **Use Case:** Open File

This use case consist in opening a jpass archive and load its passwords. The jpass may or may not be password protected. In this test we are going to explore if there are possible sneak paths and create automatic tests to the different existing paths.

### UML State Diagram
![](https://i.imgur.com/HVlcrj6.png)

### Transition tree: 5 test cases
![](https://i.imgur.com/9uJkffG.png)

### Sneak Path Testing: Transition table
| **States/Events**           | **open [file not protected]** | **click add entry** | **cancel**           | **ok** | **ok [correctPassword]** | **open [file protected]** | **ok [not correctPassword]** |
|-----------------------------|-------------------------------|---------------------|----------------------|--------|--------------------------|---------------------------|------------------------------|
| **Idle**                    | -                             | Open File Dialog    | -                    | -      | -                        | -                         | -                            |
| **Open File Dialog**        | Idle                          | .                   | Idle                 | -      | -                        | Password Protected Auth   | -                            |
| **Error Open Operation**    | -                             | -                   | -                    | Idle   | -                        | -                         | -                            |
| **Password Protected Auth** | -                             | -                   | Error Open Operation | -      | Idle                     | -                         | Error Open Operation         |


### QF-Test Tool
![](https://i.imgur.com/x2LW9fI.png)


### Description of the outcome:
1. Test-case was OpenFileSuccess, the goal was to select a existing jpass type file and open it, to do that it was necessary to have already set up a password.
2. Test-case was OpenFilePasswordIncorrect, which consisted in inserting a wrong password or leting the field empty, the aplication returned a notification and sent the use rback to the home page as expected.
3. Last test-case was OpenFileSaveLast, it consisted in opening a new file with one already opened and edited, the aplication throwed a notification to confirm if the user wants to proceed and if so, the new file will be opened normally.


The test-set passed, all the paths described in the trasition tree were complete.

### State machine test adequacy:
- State coverage: 100%
- Transition coverage: 100%
- Path coverage: 100%

### QF Tool: Sneak Path
All sneak path that we could develop would result in a failure because the aplication blocks all the buttons or text fields that would lead us to that. The way it was developed in this test case all existing sneak paths are protected.

## **Use Case:** Duplicate Entry

This use case was selected because the application allowed us to demonstrate and test a sneak path.

### UML State Diagram
![](https://i.imgur.com/wQ3Mz6T.png)


### Transition tree: 3 test cases
![](https://i.imgur.com/RZwNlRj.png)


### Sneak Path Testing: Transition table
| **States/Events**  | **Select Entry** | **Duplicate/Copy All Fields** | **Not Duplicate** |
|--------------------|------------------|-------------------------------|-------------------|
| **Idle**           | Entry Selected   | -                             | -                 |
| **Entry Selected** | -                | Entry Form                    | -                 |
| **Entry Form**     | -                | Home                          | Entry Form        |



### QF-Test Tool Screenshots
![](https://i.imgur.com/hotkG7H.png)

### Description of the outcome:
1. Test-case was NoEntrySelected, which consisted in pressing duplicate entry button without any entry selected, the aplication throws an allert box.
2. Test-case was DuplicateEntrySuccess which consisted on having a entry selected pressing the duplicate button, opening the editing/creating form and pressing ok.
3. Last test-case was DuplicateEntryFailEntryAlreadyExists that basically is clicking on duplicate and choosing a title that would already exist in that file/save.

The test-set passed with no failure, all the paths described in the trasition tree were complete.

State machine test adequacy:
- State coverage: 100%
- Transition coverage: 100%
- Path coverage: 100%


### QF Tool: Sneak Path
In this case there is a sneak path, the test-case NoEntrySelected. When a new file is opened or a entry is deleted no entry gets selected, which means that when the user tries to duplicate an entry it throws an allert, in any other state of the aplication there is always an entry selected. This means that even without any entry selected, the user can try to duplicate, which based on the transition table means it is a sneak path. However the application shows an alert and deals with this correctly.


## Feedback about QF-Test Tool
The Qf-Test tool allowed us to fulfil the basic usage of writing simple GUI tests for a Java application. In a couple of hours with the help of QF-Test tool beginner tutorials we were able to understand how to use it within our basic application. However we found the controls not so intuitive and the UI outadated and confusing. A better UI and tooltips would greatly improve beginner level user experience.

By watching the tutorials we learned that this tool enables much more powerful testing with integration in CI/CD pipelines, multiplataform testing (not specific to Java applications), video recording, etc. We didn't test these features (Out of the scope of the assignment).

Also the platform from our experience appears to really stable and ready for professional/production enviroments. We tested on Windows and MacOS without any issue. Really curious to see how the Qf-Test runner would perform on docker/linux machines for the CI/CD itegration (not tested).

However there's a caveat when comparing to it's competition: the licensing model. For a small to medium size company in Portugal, the price seems overhelming especially if there's a need to run multiple runners simultansly during CI/CD. Especially when there's proven free open source automating UI/GUI testing tools available and used as the industry standard with much bigger communities to support it (ex: Selinium).

This is also a critique to the assignment as well. We would expect for a University to give priority to reliable open source software. There's much more flexibility, transparency, learning oportunity, reduced cost and the possibility to contribute back in the future (similiar to the away academic research strives as a collaboration of multiple people). 