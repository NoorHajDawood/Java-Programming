import java.util.concurrent.atomic.AtomicIntegerArray;

public class Bakery implements MyLock {
    private AtomicIntegerArray flag;
    private AtomicIntegerArray label;
    private int n;

    public Bakery(int n) {
        this.n = n;
        flag = new AtomicIntegerArray(n);
        label = new AtomicIntegerArray(n);
        for (int i = 0; i < n; i++) {
            flag.set(i, 0);
            label.set(i, 0);
        }
    }

    @Override
    public void lock() {
        int i = ThreadID.get() % n;
        flag.set(i, 1);
        int max = 0, curr;
        for(int j = 0; j < n; j++) {
            curr = label.get(j);
            if(max < curr) {
                max = curr;
            }
        }
        label.set(i, max+1);
        boolean loop = true;
        int labeli, labelk;
        while(loop) {
            loop = false;
            for (int k = 0; !loop && k < n; k++) {
                labeli = label.get(i);
                labelk = label.get(k);
                if(flag.get(k) == 1 && (labeli > labelk || (labeli == labelk && i > k))) {
                    loop = true;
                }
            }
        }
    }

    @Override
    public void unlock() {
        int i = ThreadID.get() % n;
        flag.set(i, 0);
    }
}
