# BankSim

## Requirements
The purpose of this project is to learn multi-thread program and solve race condition and deadlock. We need to create a complete bank program that have multiple accounts doing transfer.
+ Task 1: Create UML sequence diagram to explain race condition in the program
+ Task 2: Protect against race condition --> test transfers for correctness
+ Task 3: Refactor the method of testing into a new separate thread
+ Task 4: Provide code protection (testing thread and transfer threads are running exclusively)
+ Task 5: Implement a wait/signal solution for the testing thread and transfer threads
+ Task 6: Implement a wait/notify solution to allow an account to transfer out funds if the transferring amount is greater than the account balance
+ Task 7: Implement a solution in which all threads stop transferring (the bank is closed) whenever one thread completes its transfers

## Teamwork
### Tandi:
+ Create UML sequence diagram to explain race condition in the program
+ Update the sequence diagram with task 3
+ Provide code protection (testing thread and transfer threads are running exclusively)
+ Implement a wait/signal solution for the testing thread and transfer threads
+ README.md

## Testing
We are using system testing where we test the program after each task and make sure the program works correctly. The test was done by the same person who finished the task. We made sure to test everything before merge to master branch.

## Race Condition
![UMLRaceCondition](RaceCondition.svg)
When main runs 10 threads, Each account thread will execute Transfer method.
When a thread doing withdraw and deposit, other account threads sneak another transfer method that cause overwrite the balance.
The order is undetermined and will change the total balance above or below the expected balance.

## Tester Thread
![UMLRaceCondition](RaceConditionTask3.svg)
After Task 3 is invoked, every thread will create a new test thread to do test() which check the balance every 10 transfers.
The Race Condition occur during the test() method because the thread add the total balance based on the current balance of each account,
but transfers are still taking place in other threads.


# Project Board
Link: https://trello.com/b/3nYKS152/wells-fargo-equifax
