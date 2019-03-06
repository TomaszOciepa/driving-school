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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@WebServlet(urlPatterns = "/add-student-to-course")
public class AddStudentToCourseServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddStudentToCourseServlet.class);
    private static final String TEMPLATE_NAME = "manager-course-add-student";

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

        Course course = (Course) session.getAttribute("editedCourse");
        model.put("course", course);

        checkWhatStudentsHaveCourse(model, course);
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

        Course course = (Course) session.getAttribute("editedCourse");

        int studentId = Integer.parseInt(req.getParameter("student"));
        Student newStudent = studentDao.findById(studentId);
        List<Course> courseList = newStudent.getCourses();
        courseList.add(course);
        newStudent.setCourses(courseList);
        studentDao.update(newStudent);

        model.put("course", course);
        model.put("SuccesUpdate", "Added New Student To Course");
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

    private void checkWhatStudentsHaveCourse(Map<String, Object> model, Course editedCourse) {
        List<Student> listAllStudents = studentDao.findAll();
        List<Student> listCourseStudents = editedCourse.getStudents();
        List<Student> listStudents = new ArrayList<>();

        listStudents.addAll(listAllStudents);

        if(listCourseStudents.size() == 0){
            model.put("students", listStudents);
        }else if (listCourseStudents.size() == listAllStudents.size()){
            model.put("info", "All students are enrolled in this course");
        }else {
            for (int i = 0; i < listCourseStudents.size(); i++) {
                for (int j = 0; j < listAllStudents.size(); j++) {
                    if (listCourseStudents.get(i).getStudentId() == listAllStudents.get(j).getStudentId()){
                        Student student = listAllStudents.get(j);
                        listStudents.remove(student);
                    }
                }
            }
            model.put("students", listStudents);
        }
    }
}
