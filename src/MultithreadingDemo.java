public class MultithreadingDemo {
    public static void main(String[] args) {
        count c = new count();
        // Tạo 2 thread truy cập vào cùng tài nguyên trong 1 đối tượng
        Thread t1 = new Thread(new Access("Thread-1", c));
        Thread t2 = new Thread(new Access("Thread-2", c));
        Thread t3 = new Thread(new Access("Thread-3", c));
        t1.start();
        t2.start();
        t3.start();
    }
}

class count {
    int value = 50;
    
}
class Access implements Runnable {
    String name;
    count c;
    
    public Access(String name, count c) {
        this.name = name;
        this.c = c;
    }
    
    @Override
    public void run() {
        Calc(name, c);
    }
    
    synchronized static void Calc(String name, count c) {
        for (int i = 0; i < 10; i++) {
            int temp = c.value;
            c.value--;
            System.out.println(name + " index-" + i + " before: " + temp  + " after: " + c.value);
        }
    }
}