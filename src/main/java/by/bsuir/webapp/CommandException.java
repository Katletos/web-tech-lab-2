package by.bsuir.webapp;

public class CommandException extends Exception {
    public CommandException(String msg){
        super(msg);
    }
    public CommandException(String msg, Exception e){
        super(msg, e);
    }
}
