public class Counter {
    private volatile int sum;
    private MyLock lock;
    public Counter(MyLock lock) {
        //System.out.println("In Counter Constructor()");
        sum = 0;
        this.lock = lock;
    }
    public void inc() {
        lock.lock();
        try {
            sum++;
        } finally {
            lock.unlock();
        }
    }
    public void dec() {
        lock.lock();
        try {
            sum--;
        } finally {
            lock.unlock();
        }
    }
    public int get() {
        return sum;
    }

    public void set(int c) {
        lock.lock();
        try {
            sum = c;
        } finally {
            lock.unlock();
        }
    }
}
