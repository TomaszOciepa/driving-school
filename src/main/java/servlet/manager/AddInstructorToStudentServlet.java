package servlet.manager;

import date.dao.CourseDao;
import date.dao.InstructorDao;
import date.dao.StudentDao;
import date.model.Course;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "add-instructor-to-student")
public class AddInstructorToStudentServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddInstructorToStudentServlet.class);
    private static final String TEMPLATE_NAME = "manager-instructor-add-student";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private StudentDao studentDao;
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

        Student student = (Student) session.getAttribute("editedStudent");
        model.put("student", student);

        checkWhatInstructorsStudentHas(model, student);

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        loadTemplate(writer, model, template);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        Manager managerSession = (Manager) session.getAttribute("user");
        model.put("user", managerSession);

        Student student = (Student) session.getAttribute("editedStudent");
        int instructorId = Integer.parseInt(req.getParameter("instructor"));
        Instructor instructor = instructorDao.findById(instructorId);
        List<Instructor> instructorList = student.getInstructors();
        instructorList.add(instructor);
        student.setInstructors(instructorList);
        studentDao.update(student);
        model.put("student", student);
        model.put("SuccesUpdate", "Added New Instructor To Student");
        loadTemplate(writer, model, template);

    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load template manager-student-add-course");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template manager-student-add-course");
        }
    }

    private void checkWhatInstructorsStudentHas(Map<String, Object> model, Student student) {
        List<Instructor> listAllInstructors = instructorDao.findAll();


        List<Instructor> listStudentInstructors = student.getInstructors();
        List<Instructor> listInstructors = new ArrayList<>();

        listInstructors.addAll(listAllInstructors);

        if (listStudentInstructors.size() == 0) {
            model.put("instructor", listInstructors);
        } else if (listStudentInstructors.size() == listAllInstructors.size()) {
            model.put("info", "the user is already registered for all courses");
        } else {
            for (int i = 0; i < listStudentInstructors.size(); i++) {
                for (int j = 0; j < listAllInstructors.size(); j++) {
                    if (listStudentInstructors.get(i).getInstructorId() == listAllInstructors.get(j).getInstructorId()) {
                        Instructor instructor = listAllInstructors.get(j);
                        listInstructors.remove(instructor);
                    }
                }
            }
            model.put("instructor", listInstructors);
        }
    }
}
