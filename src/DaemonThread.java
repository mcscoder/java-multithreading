public class DaemonThread extends Thread {
    
    public static void main(String[] args) {
        Runnable runnable1 = () -> {
            // never stop
            for(int i = 0; true; i++) {
                Sleep(1000);
                System.out.println(i);
            }
        };

        Runnable runnable2 = () -> {
            // terminate after 3s
            for(int i = 0; i < 3; i++) {
                Sleep(1000);
            }
        };

        Thread thread1 = new Thread(runnable1);
        thread1.setDaemon(true); // must be invoked before the thread is started
        thread1.start();

        Thread thread2 = new Thread(runnable2);
        thread2.start();

        // thread1 is daemon thread
        // thread2 is user thread
        // thread1 will terminate after thread2 terminates

        // if there are any other user threads, the daemon threads will continue to run
    }

    static void Sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
    }
}
