
/**
 * This class represents unchecked illegal release attempt exception that might occur when the user
 * try to release a MyReentrantLock or during try with resources statement including a MyReentrantLock.
 */
public class IllegalReleaseAttempt extends IllegalMonitorStateException{
    String message;

    /**
     * Constructs a new IllegalReleaseAttempt with message = null.
     */
    public IllegalReleaseAttempt(){
    }

    /**
     * Constructs a new IllegalReleaseAttempt with the given message.
     * @param message (String)
     */
    public IllegalReleaseAttempt(String message){
        this.message = message;
    }
}
