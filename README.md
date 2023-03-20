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
    <summary>Types of </summary>
    </details>