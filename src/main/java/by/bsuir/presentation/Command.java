package by.bsuir.presentation;


import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    public String execute(HttpServletRequest request) throws CommandException;
}
