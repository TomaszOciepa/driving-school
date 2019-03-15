package servlet.manager;

import authentication.CheckPassword;
import date.model.Manager;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/manager-new-password")
public class NewPasswordServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(NewPasswordServlet.class);
    private static final String TEMPLATE_CHANGE_PASSWORD = "manager-change-password";
    private static final String TEMPLATE_NEW_PASSWORD = "manager-new-password";
    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private CheckPassword checkPassword;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();
        Manager managerSession = (Manager) session.getAttribute("user");
        model.put("user", managerSession);

        String password = req.getParameter("password");

        if (checkPassword.checkManagerPassword(managerSession.getManagerEmail(), password)) {
            Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NEW_PASSWORD);
            loadTemplate(writer, model, template);
        } else {
            Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_CHANGE_PASSWORD);
            model.put("FailedUpdate", "Incorrect password. Try again");
            loadTemplate(writer, model, template);
        }
    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load NewPasswordServlet");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load NewPasswordServlet");
        }
    }
}
