/**
 * This interface represents a lock for multithreaded programming.
 */

public interface Lock extends AutoCloseable {

    /**
     * Locks the lock if it wasn't locked. If it was locked, waits for it its release and then locks it.
     */
    void acquire();

    /**
     * Tries to lock the lock. If the locked can be locked, locks it and returns true. Otherwise,
     * doesn't do anything with the lock and returns false.
     * @return (boolean) true if we locked the lock, false otherwise.
     */
    boolean tryAcquire();

    /**
     * Releases the lock. If the lock wasn't locked, or it was locked by another thread, throws
     * an IllegalReleaseAttempt exception.
     */
    void release();
}
