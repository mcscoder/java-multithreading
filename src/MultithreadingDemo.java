class MultithreadingDemo{
    public static void main(String args[]) throws InterruptedException {
        Thread thread1 = new Thread(new MultiThreading("thread 1"));
        
        System.out.println("1: " + thread1.getState());

        thread1.start();
        System.out.println("2: " + thread1.getState());


        thread1.stop();
        thread1.join();

        System.out.println("3: " + thread1.getState());
    }
}