import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.Thread;

/**
 * This class represents an autocloseable who can be reentered by the thread who locked it.
 */
public class MyReentrantLock implements Lock, AutoCloseable{

    private int timesLocked;
    private Thread currentLockThread;
    private final AtomicBoolean atomicBooleanLocked;


    /**
     * Constructs a new open (not locked) MyReentrantLock object.
     */
    public MyReentrantLock(){
        this.timesLocked = 0;
        this.currentLockThread = null;
        this.atomicBooleanLocked = new AtomicBoolean(false);
    }

    /**
     * The function locks the MyReentrantLock. If the MyReentrantLock is locked, the operation waits
     * until it is released in order to lock it.
     */
    @Override
    public void acquire() {
        while (!tryAcquire()){
            Thread.yield();
        }
    }

    /**
     * The function tries to lock the MyReentrantLock. If it can be locked the function
     * locks it and returns true. Otherwise, the function returns false.
     * @return (boolean) whether the MyReentrantLock can be locked
     */
    @Override
    public boolean tryAcquire() {
        if (Thread.currentThread().equals(this.currentLockThread) ||
                this.atomicBooleanLocked.compareAndSet(false, true)){
            this.currentLockThread = Thread.currentThread();
            this.timesLocked++;
            return true;
        }
        return false;
    }

    /**
     * The function releases the lock of the MyReentrantLock.
     * @throws IllegalReleaseAttempt if the MyReentrantLock isn't locked or the
     * current Thread isn't the Thread that locked the MyReentrantLock.
     */
    @Override
    public void release() {
        if(this.timesLocked == 0 || !Thread.currentThread().equals(this.currentLockThread)){
            throw new IllegalReleaseAttempt("Can't release");
        }
        this.timesLocked--;
        if (this.timesLocked == 0){
            this.currentLockThread = null;
            this.atomicBooleanLocked.set(false);
        }
    }

    /**
     * Closes this resource, relinquishing any underlying resources.
     * This method is invoked automatically on objects managed by the
     * try with resources statement.
     * @throws IllegalReleaseAttempt if the MyReentrantLock isn't locked or the
     * current Thread isn't the Thread that locked the MyReentrantLock.
     */
    @Override
    public void close() {
        release();
    }
}
