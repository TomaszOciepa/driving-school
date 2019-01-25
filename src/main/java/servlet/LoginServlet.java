package servlet;

import authentication.CheckExists;
import authentication.CheckPassword;
import authentication.DownloadUserToDatabase;
import date.dao.InstructorDao;
import date.dao.ManagerDao;
import date.dao.StudentDao;
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

@WebServlet(urlPatterns = ("/login"))
public class LoginServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);
    private static final String TEMPLATE_MANAGER = "manager-menu";
    private static final String TEMPLATE_INSTRUCTOR = "instructor-menu";
    private static final String TEMPLATE_STUDENT = "student-menu";
    private static final String TEMPLATE_LOGIN_FAILED = "failed-login";
    private static final String SESSION_ATTRIBUTE_EMAIL = "userEmail";
    private static final String SESSION_ATTRIBUTE_NAME = "userName";
    private static final String SESSION_ATTRIBUTE_LASTNAME = "userLastname";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private ManagerDao managerDao;
    @Inject
    private InstructorDao instructorDao;
    @Inject
    private StudentDao studentDao;

    CheckExists checkExists = new CheckExists();
    CheckPassword checkPassword = new CheckPassword();
    DownloadUserToDatabase downloadUserToDatabase = new DownloadUserToDatabase();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);
        Template template;
        Map<String, Object> model = new HashMap<>();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (!email.isEmpty() && !password.isEmpty()) {

            if (checkExists.checkManagerExists(email)) {

                if (checkPassword.checkManagerPassword(email, password)){
                    session.setAttribute(SESSION_ATTRIBUTE_EMAIL, downloadUserToDatabase.downloadManager(email).getManagerEmail());
                    session.setAttribute(SESSION_ATTRIBUTE_NAME, downloadUserToDatabase.downloadManager(email).getManagerName());
                    session.setAttribute(SESSION_ATTRIBUTE_LASTNAME, downloadUserToDatabase.downloadManager(email).getManagerLastname());

                    template = templateProvider.getTemplate(getServletContext(), TEMPLATE_MANAGER);
                    model.put("sessionEmail", downloadUserToDatabase.downloadManager(email).getManagerEmail());
                    model.put("sessionName", downloadUserToDatabase.downloadManager(email).getManagerName());
                    model.put("sessionLastname", downloadUserToDatabase.downloadManager(email).getManagerLastname());
                    showTemplate(template, model, writer);
                }
            }

        }
    }

    private void showTemplate(Template template, Map<String, Object> model, PrintWriter writer){
        try {
            template.process(model, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
