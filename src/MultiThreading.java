public class MultiThreading implements Runnable {
    private String threadName;
    public MultiThreading(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        
        for(int i = 0; true; i++) {
            System.out.println(threadName + ": " + i);
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
