package servlet.manager;

import authentication.CheckExists;
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

@WebServlet(urlPatterns = "/my-account-edit")
public class AccountManagerEditServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AccountManagerEditServlet.class);
    private static final String TEMPLATE_MY_ACCOUNT = "manager-my-account";
    private static final String TEMPLATE_MY_ACCOUNT_EDIT = "manager-my-account-edit";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private ManagerDao managerDao;
    @Inject
    private CheckExists checkExists;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();

        Manager managerSession = (Manager) session.getAttribute("user");
        model.put("user", managerSession);
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_MY_ACCOUNT_EDIT);

        loadTemplate(writer, model, template);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        Map<String, Object> model = new HashMap<>();

        HttpSession session = req.getSession(true);
        Manager sessionUser = (Manager) session.getAttribute("user");
        model.put("user", sessionUser);
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_MY_ACCOUNT);

        if (sessionUser.getManagerEmail().equals(req.getParameter("email"))) {
            updateUser(req, sessionUser);
            LOG.info("User has been updated");
            model.put("SuccesUpdate", "User has been updated");
            loadTemplate(writer, model, template);
        } else {
            if (checkExistsEmail(req.getParameter("email"))) {
                LOG.info("{} email is already busy", sessionUser.getManagerEmail());
                model.put("FailedUpdate", "Email is already busy. Try again.");
                loadTemplate(writer, model, template);
            } else {
                updateUser(req, sessionUser);
                LOG.info("User has been updated");
                model.put("SuccesUpdate", "User has been updated");
                loadTemplate(writer, model, template);
            }
        }
    }

    private void updateUser(HttpServletRequest req, Manager sessionUser) {
        sessionUser.setManagerEmail(req.getParameter("email"));
        sessionUser.setManagerName(req.getParameter("name"));
        sessionUser.setManagerLastname(req.getParameter("lastname"));
        managerDao.update(sessionUser);
    }

    private boolean checkExistsEmail(String email) {
        if (checkExists.checkManagerExists(email) || checkExists.checkInstructorExists(email) || checkExists.checkStudentExists(email)) {
            return true;
        } else {
            return false;
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
