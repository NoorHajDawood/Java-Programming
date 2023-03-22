public class Demo_Lock {
    public static void main(String[] argv) {
        System.out.println("In main() Demo_Lock");

        Counter counterPeterson = new Counter(new Peterson());
        Counter counterFilter = new Counter(new Filter(4));
        Counter counterBakery = new Counter(new Bakery(4));

        Exec_Threads eThreadsPeterson = new Exec_Threads(counterPeterson);
        Exec_Threads eThreadsFilter = new Exec_Threads(counterFilter);
        Exec_Threads eThreadsBakery = new Exec_Threads(counterBakery);

        Thread threadPeterson1 = new Thread(eThreadsPeterson);      threadPeterson1.start();
        Thread threadPeterson2 = new Thread(eThreadsPeterson);      threadPeterson2.start();

        try {
            threadPeterson1.join();
            threadPeterson2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadFilter1 = new Thread(eThreadsFilter);      threadFilter1.start();
        Thread threadFilter2 = new Thread(eThreadsFilter);      threadFilter2.start();
        Thread threadFilter3 = new Thread(eThreadsFilter);      threadFilter3.start();
        Thread threadFilter4 = new Thread(eThreadsFilter);      threadFilter4.start();

        try {
            threadFilter1.join();
            threadFilter2.join();
            threadFilter3.join();
            threadFilter4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadBakery1 = new Thread(eThreadsBakery);      threadBakery1.start();
        Thread threadBakery2 = new Thread(eThreadsBakery);      threadBakery2.start();
        Thread threadBakery3 = new Thread(eThreadsBakery);      threadBakery3.start();
        Thread threadBakery4 = new Thread(eThreadsBakery);      threadBakery4.start();

        try {
            threadBakery1.join();
            threadBakery2.join();
            threadBakery3.join();
            threadBakery4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (counterPeterson.get() == 0) {
            System.out.println("\nCounter[Peterson] final value: " + counterPeterson.get());
        } else {
            System.err.println("\n*!*!*!*! Counter[Peterson] final value: " + counterPeterson.get() + " ==> NOT = 0");
        }

        if (counterFilter.get() == 0) {
            System.out.println("\nCounter[Filter] final value: " + counterFilter.get());
        } else {
            System.err.println("\n*!*!*!*! Counter[Filter] final value: " + counterFilter.get() + " ==> NOT = 0");
        }

        if (counterBakery.get() == 0) {
            System.out.println("\nCounter[Bakery] final value: " + counterBakery.get());
        } else {
            System.err.println("\n*!*!*!*! Counter[Bakery] final value: " + counterBakery.get() + " ==> NOT = 0");
        }

        System.out.println("\nExiting main() Demo_Lock");
    }
}