# BankSim
# Race Condition
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
