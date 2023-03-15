https://www.interviewbit.com/multithreading-interview-questions/

# 16-03-2023:
1. What are two ways to implementing  thread in Java ?
    - Extending the thread class. Ex:
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
    - Implementing Runnable interface in Java
