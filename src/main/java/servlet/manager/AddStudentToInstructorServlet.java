package servlet.manager;

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
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@WebServlet(urlPatterns = "add-student-to-instructor")
public class AddStudentToInstructorServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddStudentToInstructorServlet.class);
    private static final String TEMPLATE_NAME = "manager-instructor-add-student";
    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private StudentDao studentDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();
        Manager managerSession = (Manager) session.getAttribute("user");
        model.put("user", managerSession);
        Instructor editedInstructor = (Instructor) session.getAttribute("editedInstructor");
        model.put("instructor", editedInstructor);

        checkWhatStudentsHaveInstructor(model, editedInstructor);

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

        Instructor editedInstructor = (Instructor) session.getAttribute("editedInstructor");
        int studentId = Integer.parseInt(req.getParameter("student"));
        Student newStudent = studentDao.findById(studentId);
        List<Instructor> instructorList = newStudent.getInstructors();
        instructorList.add(editedInstructor);
        newStudent.setInstructors(instructorList);
        studentDao.update(newStudent);
        model.put("instructor", editedInstructor);
        model.put("SuccesUpdate", "Added New Student To Instructor");

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);
        loadTemplate(writer, model, template);
    }

    private void checkWhatStudentsHaveInstructor(Map<String, Object> model, Instructor editedInstructor) {
        List<Student> listAllStudents = studentDao.findAll();
        List<Student> listInstructorStuents = editedInstructor.getStudents();
        List<Student> studentList = new ArrayList<>();

        studentList.addAll(listAllStudents);

        if (listInstructorStuents.size() == 0) {
            model.put("students", studentList);
        } else if (listInstructorStuents.size() == listAllStudents.size()) {
            model.put("info", "All students are enrolled in this Instructor");
        } else {
            for (int i = 0; i < listInstructorStuents.size(); i++) {
                for (int j = 0; j < listAllStudents.size(); j++) {
                    if (listInstructorStuents.get(i).getStudentId() == listAllStudents.get(j).getStudentId()) {
                        Student student = listAllStudents.get(j);
                        studentList.remove(student);
                    }
                }
            }
            model.put("students", studentList);
        }
    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load template manager-instructor-add-student");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template manager-instructor-add-student");
        }
    }
}
