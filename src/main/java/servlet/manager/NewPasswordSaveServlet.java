package servlet.manager;

import authentication.PasswordHashing;
import date.dao.ManagerDao;
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

@WebServlet(urlPatterns = "/manager-new-password-save")
public class NewPasswordSaveServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(NewPasswordSaveServlet.class);
    private static final String TEMPLATE_NEW_PASSWORD = "manager-new-password";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private ManagerDao managerDao;
    @Inject
    PasswordHashing passwordHashing;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();

        Manager managerSession = (Manager) session.getAttribute("user");
        model.put("user", managerSession);
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NEW_PASSWORD);

        String pass1 = req.getParameter("password");
        String pass2 = req.getParameter("password2");

        if(pass1.equals(pass2)){

            managerSession.setManagerPassword(passwordHashing.generateHash(pass1));
            managerDao.update(managerSession);

            model.put("SuccesUpdate", "Save new Password");
            loadTemplate(writer, model, template);
        }else {
            model.put("FailedUpdate", "The passwords are different. Try again");
            loadTemplate(writer, model, template);
        }
    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load template manager-my-account-edit");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template manager-my-account-edit");
        }
    }
}
