package paramonov.valentine.cglib;

public class SomeClass {
    public void doStuff() {
        System.out.println("Doing stuff...");
        try {
            Thread.sleep(500);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
