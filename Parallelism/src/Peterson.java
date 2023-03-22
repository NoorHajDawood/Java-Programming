import java.util.concurrent.atomic.AtomicIntegerArray;

public class Peterson implements MyLock {
    private AtomicIntegerArray flag;
    private volatile int victim;

    public Peterson () {
        flag = new AtomicIntegerArray(2);
    }

    public void lock() {
        int i = ThreadID.get() % 2;
        int j = 1 - i;
        flag.set(i, 1);
        victim = i;
        while( (flag.get(j) == 1) && (victim == i)) {}
    }

    public void unlock() {
        int i = ThreadID.get() % 2;
        flag.set(i, 0);
    }
}
