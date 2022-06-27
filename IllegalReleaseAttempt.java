public class IllegalReleaseAttempt extends IllegalMonitorStateException{
    String message;

    public IllegalReleaseAttempt(){
    }

    public IllegalReleaseAttempt(String message){
        this.message = message;
    }
}
