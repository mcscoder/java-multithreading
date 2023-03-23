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
<br>

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
<br>

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

    <br>
4. wait() and notify() method

    <details>
    <summary>wait() method</summary>

    - The wait() method causes the current thread to release the lock and wait until either another thread invokes the notify() method or the notifyAll() method for this object, or a specified amount of time has elapsed

    - The current thread must own this object's monitor, so it must be called from the synchronized method only otherwise it will throw an exception
    <br>
    
    - Waits until the object is notified
    ```java
    public final void wait() throws java.lang.InterruptedException
    ```
    <br>

    - Waits for the specified amount of time.
    ```java
    public final native void wait(long arg0) throws java.lang.InterruptedException
    ```
    </details>

    