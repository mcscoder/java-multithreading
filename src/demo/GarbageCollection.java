package demo;

public class GarbageCollection {

    public void finalize() {
        System.out.println("con me no kho vai cut");
    }

    public static void main(String args[]) {
        GarbageCollection s1 = new GarbageCollection();
        GarbageCollection s2 = new GarbageCollection();
        s1 = null;
        s2 = null;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.gc();
    }
}
