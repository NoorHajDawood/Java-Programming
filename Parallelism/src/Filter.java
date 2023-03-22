import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Filter implements MyLock {
    private AtomicIntegerArray level;
    private AtomicIntegerArray victim;
    private int n;

    public Filter(int n) {
        this.n = n;
        level = new AtomicIntegerArray(n);
        victim = new AtomicIntegerArray(n);
        for(int i = 0; i < n; i++) {
            level.set(i, 0);
        }
    }

    public void lock() {
        int i = ThreadID.get() % n;
        for (int L = 1; L < n; L++) {
            level.set(i, L);
            victim.set(L, i);
            boolean flag = true;
            while(flag) {
                flag = false;
                for (int k = 0; !flag && k < n; k++) {
                    if(k != i && level.get(k) >= L && victim.get(L) == i) {
                       flag = true;
                    }
                }
            }
        }
    }

    public void unlock() {
        int i = ThreadID.get() % n;
        level.set(i, 0);
    }
}
