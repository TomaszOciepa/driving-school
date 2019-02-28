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
import javax.validation.constraints.Max;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "details-course")
public class DetailsCourseServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DetailsCourseServlet.class);
    private static final String TEMPLATE_NAME = "manager-course-details";

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

        int id = Integer.parseInt(req.getParameter("id"));

        Course editedCourse = courseDao.findById(id);
        List<Student> studentList = editedCourse.getStudents();
        session.setAttribute("editedCourse", editedCourse);

        model.put("course", editedCourse);
        model.put("students", studentList);

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        try {
            template.process(model, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
