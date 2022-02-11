package GeekBrains;

public class Main {
    private final Object mon = new Object();
    private volatile char letterIs = 'A';

    public static void main(String[] args) {
        Main main = new Main();
        Thread t1 = new Thread(main::printA);
        Thread t2 = new Thread(main::printB);
        Thread t3 = new Thread(main::printC);
        t1.start();
        t2.start();
        t3.start();
    }

    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (letterIs != 'A') {
                        mon.wait();
                    }
                    System.out.print(" A");
                    letterIs = 'B';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (letterIs != 'B') {
                        mon.wait();
                    }
                    System.out.print(" B");
                    letterIs = 'C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (letterIs != 'C') {
                        mon.wait();
                    }
                    System.out.print(" C");
                    letterIs = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
