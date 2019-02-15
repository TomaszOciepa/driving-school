package servlet.manager;

import authentication.CheckExists;
import authentication.GenerateRandomPassword;
import authentication.PasswordHashing;
import date.dao.InstructorDao;
import date.model.Instructor;
import date.model.Manager;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import mail.SendMail;
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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/new-instructor")
public class NewInstructorServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(NewInstructorServlet.class);
    private static final String TEMPLATE_NAME = "instructor-new-instructor";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private CheckExists checkExists;
    @Inject
    private PasswordHashing passwordHashing;
    @Inject
    private GenerateRandomPassword generateRandomPassword;
    @Inject
    private SendMail mail;
    @Inject
    private InstructorDao instructorDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();

        Manager managerSession = (Manager) session.getAttribute("user");
        model.put("user", managerSession);
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);
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
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        String email = req.getParameter("email");

        if (checkExistsEmail(req.getParameter("email"))) {
            LOG.info("Email is already busy");
            model.put("FailedUpdate", "Email is already busy. Try again.");
            loadTemplate(writer, model, template);
        } else {
            Instructor newInstructor = new Instructor();
            newInstructor.setInstructorEmail(email);
            newInstructor.setInstructorName(req.getParameter("name"));
            newInstructor.setInstructorLastname("lastname");
            newInstructor.setInstructorPhone(req.getParameter("phone"));
            newInstructor.setInstructorStreet(req.getParameter("street"));
            newInstructor.setInstructorCity(req.getParameter("city"));
            newInstructor.setInstructorRole(2);
            newInstructor.setInstructorDateRegistration(LocalDate.now());
            String password = generateRandomPassword.generatePassword();
            newInstructor.setInstructorPassword(passwordHashing.generateHash(password));
            instructorDao.save(newInstructor);
            mail.sendMail(email, password);
            model.put("SuccesUpdate", "Added New Instructor");
            loadTemplate(writer, model, template);

        }

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
            LOG.info("Load template instructor-new-instructor");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template instructor-new-instructor");
        }
    }
}
