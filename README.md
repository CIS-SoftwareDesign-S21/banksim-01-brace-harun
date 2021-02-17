# BankSim
# Race Condition
![UMLRaceCondition](RaceCondition.svg)
When main runs 10 threads, Each account thread will execute Transfer method.
Race Condition occur during withdraw where an account thread can't execute deposit method and other account thread sneak withdraw method before the first thread can execute deposit method.
