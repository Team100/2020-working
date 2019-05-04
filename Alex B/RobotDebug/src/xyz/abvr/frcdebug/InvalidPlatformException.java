package xyz.abvr.frcdebug;

public class InvalidPlatformException extends RuntimeException {
    public InvalidPlatformException(){
        super();
    }
    public InvalidPlatformException(String msg){
        super(msg);
    }
}
