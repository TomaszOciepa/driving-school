package servlet.login;

import authentication.CheckExists;
import authentication.CheckPassword;
import authentication.DownloadUserToDatabase;
import date.model.Instructor;
import date.model.Manager;
import date.model.Student;
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
    private static final String TEMPLATE_MANAGER = "manager-start";
    private static final String TEMPLATE_INSTRUCTOR = "instructor-start";
    private static final String TEMPLATE_STUDENT = "student-start";
    private static final String TEMPLATE_LOGIN_FAILED = "failed-login";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private CheckExists checkExists;
    @Inject
    private CheckPassword checkPassword;
    @Inject
    private DownloadUserToDatabase downloadUserToDatabase;

    Template template;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (!email.isEmpty() && !password.isEmpty()) {
            LOG.info("LoginServlet.java: Start Authentication. {}", email);

            if (checkExists.checkManagerExists(email)) {
                LOG.info("Login as Manager");
                if (checkPassword.checkManagerPassword(email, password)) {
                    LOG.info("Manager password correct");

                    Manager manager = downloadUserToDatabase.downloadManager(email);
                    session.setAttribute("user", manager);
                    Manager managerSession = (Manager) session.getAttribute("user");
                    model.put("user", managerSession);

                    template = templateProvider.getTemplate(getServletContext(), TEMPLATE_MANAGER);
                    LOG.info("Loaded template: {}", TEMPLATE_MANAGER);

                    showTemplate(template, model, writer);

                } else {
                    LOG.warn("Failed password Manager");
                    loadFailedTemplate(writer, model);
                }
            } else if (checkExists.checkInstructorExists(email)) {
                LOG.info("Login as Instructor");
                if (checkPassword.checkInstructorPassword(email, password)) {
                    LOG.info("Instructor password correct");

                    Instructor instructor = downloadUserToDatabase.downloadInstructor(email);
                    session.setAttribute("user", instructor);
                    Instructor instructorSession = (Instructor) session.getAttribute("user");
                    model.put("user", instructorSession);

                    template = templateProvider.getTemplate(getServletContext(), TEMPLATE_INSTRUCTOR);
                    LOG.info("Loaded template: {}", TEMPLATE_INSTRUCTOR);

                    showTemplate(template, model, writer);
                } else {
                    LOG.warn("Failed password Instructor");
                    loadFailedTemplate(writer, model);
                }
            } else if (checkExists.checkStudentExists(email)) {
                LOG.info("Login as Student");
                if (checkPassword.checkStudentPassword(email, password)) {
                    LOG.info("Student password correct");

                    Student student = downloadUserToDatabase.downloadStudent(email);
                    session.setAttribute("user", student);
                    Student studentSession = (Student) session.getAttribute("user");
                    model.put("user", studentSession);

                    template = templateProvider.getTemplate(getServletContext(), TEMPLATE_STUDENT);
                    LOG.info("Loaded template: {}", TEMPLATE_STUDENT);

                    showTemplate(template, model, writer);

                } else {
                    LOG.warn("LoginServlet.java: Failed password Student");
                    loadFailedTemplate(writer, model);
                }
            } else {
                LOG.warn("LoginServlet.java: User not found in database");
                loadFailedTemplate(writer, model);
            }

        } else {
            LOG.warn("LoginServlet.java: Email or Password Empty");
            loadFailedTemplate(writer, model);
        }
    }

    private void loadFailedTemplate(PrintWriter writer, Map<String, Object> model) throws IOException {
        template = templateProvider.getTemplate(getServletContext(), TEMPLATE_LOGIN_FAILED);
        showTemplate(template, model, writer);
    }

    private void showTemplate(Template template, Map<String, Object> model, PrintWriter writer) {
        try {
            template.process(model, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
