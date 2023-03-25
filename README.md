https://www.interviewbit.com/multithreading-interview-questions/

# 16-03-2023:

## 1. Difference ways to implement thread

<details>
<summary>Extending the thread class.</summary>

```java
class MultithreadingDemo extends Thread {
    public void run() {
        // Do something here
        System.out.println("My thread is in running state.");
    }

    public static void main(String args[]) {
        MultithreadingDemo myThread = new MultithreadingDemo();

        myThread.start();
    }
}
```
</details>


<details>
<summary>Implementing Runnable interface in Java.</summary>

```java
class MultithreadingDemo implements Runnable {
    public void run() {
        // Do something here
        System.out.println("My thread is in running state.");
    }

    public static void main(String args[]) {
        Thread myThread = new Thread(new MultithreadingDemo());

        myThread.start();
    }
}
```
</details>


<details>
<summary>Anonymous class.</summary>

```java
class MultithreadingDemo {
    public static void main(String args[]){
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Do something here
                System.out.println("My thread is in running state.");
            }            
        });

        myThread.start();
    }
}
```
</details>


<details>
<summary>Lambda expression</summary>

```java
class MultithreadingDemo {
    public static void main(String args[]){
        Runnable runnable = () -> {
            // Do something here
            System.out.println("My thread is in running state.");
        };

        Thread myThread = new Thread(runnable);
        myThread.start();
    }
}
```
</details>

---

## 2. Life Cycle of a thread
<details>
<summary>1. New</summary>

- When a new thread is created, has not yet started
```java
Thread myThread = new Thread(); // the thread is now in the New state
``` 
</details>


<details>
<summary>2. Runnable</summary>

- When a thread is executing or ready to execute
```java
    Thread myThread = new Thread();
    myThread.start(); // the thread is now in the Runnable state
```
</details>


<details>
<summary>3. Blocked / Waiting</summary>

- Blocked: When a thread is waiting to acquire a monitor lock to enter or re-enter a synchronized

```java
public class TestSynchronization {
    public static void main(String[] args) {
        Count c = new Count();
        
        // Both thread access to a resource in an object
        Thread thread0 = new Thread() { // New state
            @Override
            public void run() {
                c.countToFive();
            }
        };
        
        Thread thread1 = new Thread() { // New state
            @Override
            public void run() {
                c.countToFive();
            }
        };

        c.setThread(thread0, thread1);
        System.out.println();

        thread0.start(); // Runnable state
        thread1.start(); // Blocked state (blocked by thread0)
    }
}

class Count {
    Thread[] _thread;
    public void setThread(Thread... _thread) {
        this._thread = _thread;
    }
    public synchronized void countToFive() {
        for(int i = 0; i < 5; i++) {
            for(Thread thread : _thread) {
                System.out.println(thread.getName() + " " + thread.getState());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
```

- Waiting: When a thread is waiting for some thread to perform a particular action without any time limit
```java
public class WaitingState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public synchronized void run() {
                try {
                    wait(); // Thread now is Waiting state
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        thread1.start();
        System.out.println();
        System.out.println(thread1.getState()); // output: WAITING
    }
}
```

</details>

<details>
<summary>4. Timed Waiting</summary>

- When a thread is waiting for some thread to perform a specific action for a specified period
```java
public class TestSynchronization {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(10);
        System.out.println(t.getState()); // Timed Waiting state
    }
}
```

</details>

<details>
<summary>5. Terminated</summary>

- When a thread has completed its execution
```java
Thread t = new Thread();
t.start();
t.join();
System.out.println(t.getState()); // Terminated state
```
</details>

---

## 3. Thread Synchronization:
<details>
<summary>What is thread synchronization</summary>

- Synchronization in Java is the capability to control the access of multiple threads to any shared resources

- A piece of logic marked with synchronized becomes a synchronized block, allowing one thread to execute at any given time
</details>

<details>
<summary>Why use Synchronization</summary>

- The synchronization is mainly used to avoid the <a href="https://www.baeldung.com/cs/race-conditions" target="_top">Race condition</a>
</details>

<details>
<summary>Types of Synchronization</summary>

- There are two types of synchronization:
<ol style="list-style-type: decimal">
    <li>Process Synchronization</li>
    <li>Thread Synchronization</li>
</ol>


</details>

<details>
<summary>Thread Synchronization</summary>

- There are two types of thread synchronization mutual exclusive and inter-thread communication

1. Mutual Exclusive:
    1. Synchronized method
    1. Synchronized block
    1. Static synchronization

1. Cooperation (Inter-thread communication in Java)
</details>

<details>
<summary>Mutual Exclusive</summary>

- Mutual Exclusive helps keep threads from interfering with one another while sharing data. 

- It can be achieved by using the following there ways:
    1. By Using Synchronized Method
    2. By Using Synchronized Block
    3. By Using Static Synchronization
</details>

<details>
<summary>Understanding the problem without problem</summary>

- In this example, there is no synchronization, so the output is inconsistent

```java
class Table {
    void printTable(int n) { // method is not synchronized
        for (int i = 1; i <= 5; i++) {
            System.out.println(n * i);
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class MyThread1 extends Thread {
    Table t;

    MyThread1(Table t) {
        this.t = t;
    }

    public void run() {
        t.printTable(5);
    }
}

class MyThread2 extends Thread {
    Table t;

    MyThread2(Table t) {
        this.t = t;
    }

    public void run() {
        t.printTable(100);
    }
}

public class TestSynchronization {
    public static void main(String args[]) {
        Table obj = new Table();// only one object
        
        // a resource is accessed by two threads
        MyThread1 t1 = new MyThread1(obj);
        MyThread2 t2 = new MyThread2(obj);
        t1.start();
        t2.start();
    }
}
```
</details>

<details>
<summary>Java Synchronized method</summary>

- If you declare any method as synchronized, it is known as a synchronized method.
- The synchronized method is used to lock an object for any shared resource.

```java
class Table {
    synchronized void printTable(int n) { // method is synchronized
        for (int i = 1; i <= 5; i++) {
            System.out.println(n * i);
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class MyThread1 extends Thread {
    Table t;

    MyThread1(Table t) {
        this.t = t;
    }

    public void run() {
        t.printTable(5);
    }
}

class MyThread2 extends Thread {
    Table t;

    MyThread2(Table t) {
        this.t = t;
    }

    public void run() {
        t.printTable(100);
    }
}

public class TestSynchronization {
    public static void main(String args[]) {
        Table obj = new Table();// only one object
        
        // two threads access to a resource
        MyThread1 t1 = new MyThread1(obj);
        MyThread2 t2 = new MyThread2(obj);
        t1.start();
        t2.start();
    }
}
```
</details>

---

## 4. wait() and notify() method
<details>
<summary>wait() method</summary>

- The wait() method causes the current thread to release the lock and wait until either another thread invokes the notify() method or the notifyAll() method for this object, or a specified amount of time has elapsed

- The current thread must own this object's monitor, so it must be called from the synchronized method only otherwise it will throw an exception

- Waits until the object is notified
```java
public final void wait() throws java.lang.InterruptedException
```

- Waits for the specified amount of time.
```java
public final native void wait(long arg0) throws java.lang.InterruptedException
```
</details>

<details>
<summary>notify() method</summary>

- The notify() method wakes up a single thread that is waiting on this object's monitor. If any threads are waiting on this object, one of them is chosen to be awakened.

- Syntax:
```java
public final void notify()
```
</details>

<details>
<summary>Difference between wait and sleep</summary>

| wait() | sleep() |
| --- | --- |
The wait() method release the lock | The sleep() method doesn't release the lock
It is a method of Object class | It is method of Thread class
It is the non-static method | It is the static method
It should be notified by notify() or notifyAll() methods | After the specified amount of time, sleep is completed
</details>

<details>
<summary>Code demo</summary>

```java
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
```
</details>

---

## 5. Deadlock
<details>
<summary>Deadlock in Java</summary>

Deadlock in Java occurs when two or more threads are blocked forever, waiting for each other to release the resources they need to continue execution.

<img src="https://static.javatpoint.com/images/java-deadlock.png">
</details>

<details>
<summary>Example of Deadlock in Java</summary>

```java
public class DeadlockDemo {
    public static void main(String[] args) {
        final String resource1 = "heeloo";
        final String resource2 = "pye pie";

        // t1 tries to lock resource1 then resource2
        Thread t1 = new Thread() {
            public void run() {
                synchronized (resource1) {
                    System.out.println("Thread 1: locked resource 1");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    synchronized (resource2) {
                        System.out.println("Thread 1: locked resource 2");
                    }
                }
            }
        };

        // t2 tries to lock resource2 then resource1
        Thread t2 = new Thread() {
            public void run() {
                synchronized (resource2) {
                    System.out.println("Thread 2: locked resource 2");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    synchronized (resource1) {
                        System.out.println("Thread 2: locked resource 1");
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }
}
```
</deadlock>

