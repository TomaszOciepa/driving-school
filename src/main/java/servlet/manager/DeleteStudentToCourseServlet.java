package servlet.manager;

import date.dao.CourseDao;
import date.dao.StudentDao;
import date.model.Course;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@WebServlet(urlPatterns = "/delete-student-to-course")
public class DeleteStudentToCourseServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteStudentToCourseServlet.class);
    private static final String TEMPLATE_NAME = "manager-course-delete-student";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private CourseDao courseDao;
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

        Course editedCourse = (Course) session.getAttribute("editedCourse");
        List<Student> studentList = editedCourse.getStudents();

        model.put("students", studentList);
        model.put("course", editedCourse);

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
        
        Course editedCourse = (Course) session.getAttribute("editedCourse");
        int studentId = Integer.parseInt(req.getParameter("student"));
        Student studentDeleteCourse = studentDao.findById(studentId);

        List<Course> courseList = studentDeleteCourse.getCourses();
        int idCoureDelete = editedCourse.getCourseId();

        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseId() == idCoureDelete) {
                courseList.remove(i);
                studentDeleteCourse.setCourses(courseList);
                studentDao.update(studentDeleteCourse);
                model.put("SuccesUpdate", "Delete Student for Course");
                model.put("course", editedCourse);
            }
        }
        loadTemplate(writer, model, template);
    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load template manager-student-delete-course");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template manager-student-delete-course");
        }
    }
}
