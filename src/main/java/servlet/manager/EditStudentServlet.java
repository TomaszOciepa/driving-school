package servlet.manager;

import authentication.CheckExists;
import date.dao.StudentDao;
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

@WebServlet(urlPatterns = "/edit-student")
public class EditStudentServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(EditStudentServlet.class);
    private static final String TEMPLATE_NAME = "manager-student-edit";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private StudentDao studentDao;
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

        int id = Integer.parseInt(req.getParameter("id"));
        Student editedStudent = studentDao.findById(id);

        session.setAttribute("editedStudent", editedStudent);
        model.put("student", editedStudent);
        model.put("course", editedStudent.getCourses());
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        loadTemplate(writer, model, template);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();

        Manager managerSession = (Manager) session.getAttribute("user");
        model.put("user", managerSession);

        Student editedStudent = (Student) session.getAttribute("editedStudent");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastname");
        String phone = req.getParameter("phone");
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String pesel = req.getParameter("pesel");

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        if (checkExists.checkStudentExists(email)) {

            if (editedStudent.getStudentEmail().equals(email)){
                updateStudent(editedStudent, email, name, lastName, phone, street, city, pesel);
                LOG.info("Student has bean update");
                model.put("student", editedStudent);
                model.put("SuccesUpdate", "Student has bean update");
                loadTemplate(writer, model, template);
            }else {
                LOG.info("Email is already busy");
                model.put("student", editedStudent);
                model.put("FailedUpdate", "Email is already busy. Try again.");
                loadTemplate(writer, model, template);
            }
        } else {
            updateStudent(editedStudent, email, name, lastName, phone, street, city, pesel);
            LOG.info("Student has bean update");
            model.put("student", editedStudent);
            model.put("SuccesUpdate", "Student has bean update");
            loadTemplate(writer, model, template);
        }


    }

    private void updateStudent(Student editedStudent, String email, String name, String lastName, String phone, String street, String city, String pesel) {
        editedStudent.setStudentEmail(email);
        editedStudent.setStudentEmail(name);
        editedStudent.setStudentLastname(lastName);
        editedStudent.setStudentPhone(phone);
        editedStudent.setStudentStreet(street);
        editedStudent.setStudentCity(city);
        editedStudent.setStudentPESEL(pesel);
        studentDao.update(editedStudent);
    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load template manager-edit-student");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template manager-edit-student");
        }
    }
}
