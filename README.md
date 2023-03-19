https://www.interviewbit.com/multithreading-interview-questions/

# 16-03-2023:
<details>

<summary>1. Difference ways to implement thread<summary>

- Extending the thread class.
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
    
- Implementing Runnable interface in Java.
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

- Some others way to implementing thread:
    + Anonymous class:
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

    + Lambda expression:
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
    </details>

    <details>
    <summary>2. Active</summary>

    </details>

    <details>
    <summary>3. Blocked / Waiting</summary>

    </details>

    <details>
    <summary>4. Timed Waiting</summary>

    </details>

    <details>
    <summary>5. Terminated</summary>

    </details>
