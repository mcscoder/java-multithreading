https://www.interviewbit.com/multithreading-interview-questions/

# 16-03-2023:
1. What are two ways to implementing thread in Java ?
    - Extending the thread class.
        ```java
        class MultithreadingDemo extends Thread {
            public void run() {
                System.out.println("My thread is in running state.");
            }

            public static void main(String args[]) {
                MultithreadingDemo obj = new MultithreadingDemo();
                obj.start();
            }
        }
        ```
        
    - Implementing Runnable interface in Java.
        ```java
        class MultithreadingDemo implements Runnable {
            public void run() {
                System.out.println("My thread is in running state.");
            }

            public static void main(String args[]) {
                MultithreadingDemo obj = new MultithreadingDemo();
                Thread tobj = new Thread(obj);
                tobj.start();
            }
        }
        ```

2. Life Cycle of a thread
    - 1. New
    - 2. Active
    - 3. Blocked / Waiting
    - 4. Timed Waiting
    - 5. Terminated

    
    + 1. New: Whenever a new thread is created, it is always in the new state.

    + 2. Active: When a thread invokes the start() method, it moves from the new state to the active state. The active state contains two states within it:
        ++ Runnable: A thread 