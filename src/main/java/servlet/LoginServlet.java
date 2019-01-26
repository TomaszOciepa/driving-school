package servlet;

import authentication.CheckExists;
import authentication.CheckPassword;
import authentication.DownloadUserToDatabase;
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
                LOG.info("LoginServlet.java: Check exists Manager email");
                if (checkPassword.checkManagerPassword(email, password)) {
                    LOG.info("Check exists Manager password");
                    session.setAttribute(SESSION_ATTRIBUTE_EMAIL, downloadUserToDatabase.downloadManager(email).getManagerEmail());
                    session.setAttribute(SESSION_ATTRIBUTE_NAME, downloadUserToDatabase.downloadManager(email).getManagerName());
                    session.setAttribute(SESSION_ATTRIBUTE_LASTNAME, downloadUserToDatabase.downloadManager(email).getManagerLastname());

                    template = templateProvider.getTemplate(getServletContext(), TEMPLATE_MANAGER);
                    LOG.info("LoginServlet.java: Loaded template manager");

                    model.put("sessionEmail", downloadUserToDatabase.downloadManager(email).getManagerEmail());
                    model.put("sessionName", downloadUserToDatabase.downloadManager(email).getManagerName());
                    model.put("sessionLastname", downloadUserToDatabase.downloadManager(email).getManagerLastname());
                    showTemplate(template, model, writer);
                } else {
                    LOG.warn("LoginServlet.java: Failed password Manager");
                    loadFailedTemplate(writer, model);
                }
            } else if (checkExists.checkInstructorExists(email)) {
                LOG.info("LoginServlet.java: Check exists Instructor email");
                if (checkPassword.checkInstructorPassword(email, password)) {
                    LOG.info("Check exists Instructor password");

                    session.setAttribute(SESSION_ATTRIBUTE_EMAIL, downloadUserToDatabase.downloadInstructor(email).getInstructorEmail());
                    session.setAttribute(SESSION_ATTRIBUTE_NAME, downloadUserToDatabase.downloadInstructor(email).getInstructorName());
                    session.setAttribute(SESSION_ATTRIBUTE_LASTNAME, downloadUserToDatabase.downloadInstructor(email).getInstructorLastname());

                    template = templateProvider.getTemplate(getServletContext(), TEMPLATE_INSTRUCTOR);
                    LOG.info("LoginServlet.java: Loaded template instructor");

                    model.put("sessionEmail", downloadUserToDatabase.downloadInstructor(email).getInstructorEmail());
                    model.put("sessionName", downloadUserToDatabase.downloadInstructor(email).getInstructorName());
                    model.put("sessionLastname", downloadUserToDatabase.downloadInstructor(email).getInstructorLastname());
                    showTemplate(template, model, writer);
                } else {
                    LOG.warn("LoginServlet.java: Failed password Instructor");
                    loadFailedTemplate(writer, model);
                }
            } else if (checkExists.checkStudentExists(email)) {
                LOG.info("LoginServlet.java: Check exists Student email");
                if (checkPassword.checkStudentPassword(email, password)) {
                    LOG.info("Check exists Student password");

                    session.setAttribute(SESSION_ATTRIBUTE_EMAIL, downloadUserToDatabase.downloadStudent(email).getStudentEmail());
                    session.setAttribute(SESSION_ATTRIBUTE_NAME, downloadUserToDatabase.downloadStudent(email).getStudentName());
                    session.setAttribute(SESSION_ATTRIBUTE_LASTNAME, downloadUserToDatabase.downloadStudent(email).getStudentLastname());

                    template = templateProvider.getTemplate(getServletContext(), TEMPLATE_STUDENT);
                    LOG.info("LoginServlet.java: Loaded template student");

                    model.put("sessionEmail", downloadUserToDatabase.downloadStudent(email).getStudentEmail());
                    model.put("sessionName", downloadUserToDatabase.downloadStudent(email).getStudentName());
                    model.put("sessionLastname", downloadUserToDatabase.downloadStudent(email).getStudentLastname());
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
