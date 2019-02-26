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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/add-course-to-student")
public class AddCourseToStudentServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddCourseToStudentServlet.class);
    private static final String TEMPLATE_NAME = "manager-student-add-course";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private StudentDao studentDao;
    @Inject
    private CourseDao courseDao;

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

        checkWhatCoursesStudentHas(model, student);

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

        int coursId = Integer.parseInt(req.getParameter("course"));
        Course newCourse = courseDao.findById(coursId);
        List<Course> listStudentCourse = student.getCourses();
        listStudentCourse.add(newCourse);
        student.setCourses(listStudentCourse);

        studentDao.update(student);
        model.put("student", student);
        model.put("SuccesUpdate", "Added New Course To Student");
        loadTemplate(writer, model, template);
    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load template manager-edit-student");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template manager-edit-student");
        }
    }

    private void checkWhatCoursesStudentHas(Map<String, Object> model, Student student) {
        List<Course> listAllCoures = courseDao.findAll();

        List<Course> listStudentCourse = student.getCourses();
        List<Course> listCourse = new ArrayList<>();

        listCourse.addAll(listAllCoures);

        if (listStudentCourse.size() == 0){
            model.put("courses", listCourse);
        }else if (listStudentCourse.size() == listAllCoures.size()){
            model.put("info", "the user is already registered for all courses");
        } else {
            for (int i = 0; i < listStudentCourse.size(); i++) {
                for (int j = 0; j < listAllCoures.size(); j++) {
                    if (listStudentCourse.get(i).getCourseId() == listAllCoures.get(j).getCourseId()) {
                        Course course = listAllCoures.get(j);
                        listCourse.remove(course);
                    }
                }
            }
            model.put("courses", listCourse);
        }
    }
}
