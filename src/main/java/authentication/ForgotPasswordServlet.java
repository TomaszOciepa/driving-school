package authentication;

import date.dao.InstructorDao;
import date.dao.ManagerDao;
import date.dao.StudentDao;
import date.model.Instructor;
import date.model.Manager;
import date.model.Student;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/remember-password")
public class ForgotPasswordServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ForgotPasswordServlet.class);
    private static final String TEMPLATE_NAME = "forgot-password";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private SendMail mail;
    @Inject
    private CheckExists checkExists;
    @Inject
    private PasswordHashing passwordHashing;
    @Inject
    private GenerateRandomPassword generateRandomPassword;
    @Inject
    private ManagerDao managerDao;
    @Inject
    private InstructorDao instructorDao;
    @Inject
    private StudentDao studentDao;
    @Inject
    private DownloadUserToDatabase downloadUserToDatabase;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);
        model.put("giveMail", "enter your email");

        loadTemplate(writer, model, template);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter writer = resp.getWriter();
        Map<String, Object> model = new HashMap<>();
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        String email = req.getParameter("email");


        if (checkExists.checkManagerExists(email)){
            Manager manager = downloadUserToDatabase.downloadManager(email);
            String password = generateRandomPassword.generatePassword();
            manager.setManagerPassword(passwordHashing.generateHash(password));
            managerDao.update(manager);
            model.put("sendMail", "check your email");
            mail.sendMail(email, password);
            loadTemplate(writer, model, template);
        } else if (checkExists.checkInstructorExists(email)){
            Instructor instructor = downloadUserToDatabase.downloadInstructor(email);
            String password = generateRandomPassword.generatePassword();
            instructor.setInstructorPassword(passwordHashing.generateHash(password));
            instructorDao.update(instructor);
            model.put("sendMail", "check your email");
            mail.sendMail(email, password);
        } else if (checkExists.checkStudentExists(email)){
            Student student = downloadUserToDatabase.downloadStudent(email);
            String password = generateRandomPassword.generatePassword();
            student.setStudentPassword(passwordHashing.generateHash(password));
            studentDao.update(student);
            model.put("sendMail", "check your email");
            loadTemplate(writer, model, template);
        } else {
            model.put("errorSendMail", "check your email");
            loadTemplate(writer, model, template);
        }



    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            template.process(model, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
