package servlet.manager;

import date.dao.CourseDao;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "edit-course")
public class EditCourseServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(EditCourseServlet.class);
    private static final String TEMPLATE_NAME = "manager-course-edit";

    @Inject
    private TemplateProvider templateProvider;
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

       Course editedCourse = (Course) session.getAttribute("editedCourse");
       List<Student> studentList = editedCourse.getStudents();
        model.put("course", editedCourse);
        model.put("students", studentList);

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

        Course editedCourse = (Course) session.getAttribute("editedCourse");

        editedCourse.setCourseName(req.getParameter("name"));
        editedCourse.setCourseDataStart(req.getParameter("dateStart"));
        editedCourse.setCourseDataFinish(req.getParameter("dateFinish"));
        editedCourse.setCourseCatrgoryDriveLicense(req.getParameter("category"));
        editedCourse.setCourseNumberHours(req.getParameter("hours"));

        courseDao.update(editedCourse);
        model.put("course", editedCourse);

        model.put("SuccesUpdate", "Course has bean update");
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);
        loadTemplate(writer, model, template);

    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load template manager-course-edit");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template manager-course-edit");
        }
    }
}
