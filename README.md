https://www.interviewbit.com/multithreading-interview-questions/

# 16-03-2023:
1. Difference ways to implement thread

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

2. Life Cycle of a thread
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

    - Waiting: When a thread is waiting for some thread to perform a particular action without any time limit

    </details>

    <details>
    <summary>4. Timed Waiting</summary>

    - When a thread is waiting for some thread to perform a specific action for a specified period

    </details>

    <details>
    <summary>5. Terminated</summary>

    - When a thread has completed its execution

    </details>

3. Thread Synchronization:
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

    - There are two types of synchronization
        1. Process Synchronization
        2. Thread Synchronization
    </details>

    <details>
    <summary>Thread Synchronization</summary>

    - There are two types of thread synchronization mutual exclusive and inter-thread communication

        1. Mutual Exclusive:
            1. Synchronized method
            2. Synchronized block
            3. Static synchronization
        2. Cooperation (Inter-thread communication in Java)
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

    public class TestSynchronization2 {
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

    ```java
    
    ```
    </details>