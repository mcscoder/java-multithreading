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