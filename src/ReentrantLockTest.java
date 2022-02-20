import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        Task task = new Task();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                task.firstThread();
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.secondThread();
            }
        });
        thread.start();
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.showCounter();


    }
}

class Task {
    Lock lock = new ReentrantLock();
    private int counter;

    private void increment() {
        for(int i = 0; i < 10000; i++)
            counter++;
    }

    public void firstThread() {
        lock.lock();
        increment();
        lock.unlock();
    }

    public void secondThread() {
        lock.lock();
        increment();
        lock.unlock();

    }
    public void showCounter() {
        System.out.println(counter);
    }
}
