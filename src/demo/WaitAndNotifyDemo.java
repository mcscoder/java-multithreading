package demo;

import java.util.Scanner;

class Bank {
    private int balance;
    private int widthDrawAmount = -1;

    public Bank(int balance) {
        this.balance = balance;
    }

    public synchronized void widthDraw(int amount) {
        widthDrawAmount = amount;
        if (balance < amount) {
            System.out.println("deo du tien ma doi rut, danh chetme may gio");
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        balance -= amount;
        System.out.println("rut tien thanh cong");
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("nap tien thanh cong");
        if (widthDrawAmount != -1 && balance > widthDrawAmount) {
            notify();
        }
    }
}

public class WaitAndNotifyDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Bank bank = new Bank(4000);

        // create two threads access into a resource in an object
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                bank.widthDraw(5000);
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                bank.deposit(in.nextInt());
            }
        };

        thread1.start();
        thread2.start();
    }
}
