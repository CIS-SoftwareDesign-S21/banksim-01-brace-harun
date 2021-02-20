package edu.temple.cis.c3238.banksim;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 * @author Modified by Alexa Delacenserie
 * @author Modified by Tarek Elseify
 */

public class Bank {

    public static final int NTEST = 10;
    private final Account[] accounts;
    private long numTransactions = 0;
    private final int initialBalance;
    private final int numAccounts;
    public boolean signal = false;

    public Bank(int numAccounts, int initialBalance) {
        this.initialBalance = initialBalance;
        this.numAccounts = numAccounts;
        accounts = new Account[numAccounts];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, initialBalance);
        }
        numTransactions = 0;
    }

    public synchronized void transfer(int from, int to, int amount) throws InterruptedException {
        //avoid deadlock in case of withdraw( a, b ) and deposit( b, a ) occurring simultaneosly
        int lesser = Math.min( from, to );
        int greater = Math.max( from, to );

        synchronized ( accounts[lesser] ) {
            synchronized ( accounts[greater] ) {
                if (accounts[from].withdraw(amount)) {
                    accounts[to].deposit(amount);
                    System.out.printf("Account %d successfully transferred $%d to Account %d.\n", from, amount, to);
                } else
                    System.out.printf("Transfer of $%d from Account %d to Account %d failed.\n", amount, from, to);


                // Uncomment line when ready to start Task 3.
                if (shouldTest())
                    signal = true;
                    test();
                    signal = false;


                while(signal){
                    notifyAll();
                    wait();
                }
            }
        }
    }

    public int getNumAccounts() {
        return numAccounts;
    }
    
    
    public boolean shouldTest() {
        return ++numTransactions % NTEST == 0;
    }

    public void test() {
        new Tester( accounts, initialBalance, numAccounts, Thread.currentThread()).start();
    }

}


class Tester extends Thread {

    private final Account[] accounts;
    private final int initialBalance;
    private final int numAccounts;
    private Thread transferThread;

    public Tester(Account[] accounts, final int intialBalance, final int numAccounts, Thread transferThread) {
        this.accounts = accounts;
        this.initialBalance = intialBalance;
        this.numAccounts = numAccounts;
        this.transferThread = transferThread;
    }

    @Override
    public synchronized void run() {
                try {
                    wait();
                    System.out.println("I AM HERE !!!!");
                    int totalBalance = 0;
                    for (Account account : accounts) {
                        System.out.printf("%-30s %s%n",
                                transferThread.toString(), account.toString());
                        totalBalance += account.getBalance();
                    }
                    System.out.printf("%-30s Total balance: %d\n", transferThread.toString(), totalBalance);
                    if (totalBalance != numAccounts * initialBalance) {
                        System.out.printf("%-30s Total balance changed!\n", transferThread.toString());
                        System.exit(0);
                    } else {
                        System.out.printf("%-30s Total balance unchanged.\n", transferThread.toString());
                    }
                    notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

    }

}