package by.bsuir.webapp.commands;

import by.bsuir.application.impementation.PrescriptionService;
import by.bsuir.webapp.Command;
import by.bsuir.webapp.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class GetPrescriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        var service = new PrescriptionService();
//        try {
//            List<Object> info = service.getUserPrescriptions(request.getParameter(RequestParameterName.USER_ID));
//            request.setAttribute(RequestParameterName.SIMPLE_INFO, info);
//            return JspPageName.PRESCRIPTIONS_PAGE;
//        } catch (BuisenessRuleException e) {
//            throw new CommandException("", e);
//        }

    return null;
    }
}
