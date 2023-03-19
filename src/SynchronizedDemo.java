import javax.management.ValueExp;

public class SynchronizedDemo extends Thread {
    private int value;

    public SynchronizedDemo(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        Calc(currentThread().getName(), value);
    }
    
    synchronized static void Calc(String name, int value) {
        for (int i = 0; i < 10; i++) {
            int temp = value;
            value--;
            System.out.println(name + " index-" + i + " before: " + temp  + " after: " + value);
        }
    }

    public static void main(String[] args) {
        int value = 50;
        // create three threads accessing a resource in an object
        SynchronizedDemo thread1 = new SynchronizedDemo(value);
        SynchronizedDemo thread2 = new SynchronizedDemo(value);
        SynchronizedDemo thread3 = new SynchronizedDemo(value);
        thread1.start(); 
        thread2.start();
        thread3.start();

    }
}
