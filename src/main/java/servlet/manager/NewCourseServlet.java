package servlet.manager;

import date.dao.CourseDao;
import date.model.Course;
import date.model.Manager;
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

@WebServlet(urlPatterns = "new-course")
public class NewCourseServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(NewCourseServlet.class);
    private static final String TEMPLATE_NAME = "manager-new-course";

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

        String name = req.getParameter("name");
        String dateStart = req.getParameter("dateStart");
        String dateFinish = req.getParameter("dateFinish");
        String category = req.getParameter("category");
        String hours = req.getParameter("hours");

        Course newCourse = new Course();
        newCourse.setCourseName(name);
        newCourse.setCourseDataStart(dateStart);
        newCourse.setCourseDataFinish(dateFinish);
        newCourse.setCourseCatrgoryDriveLicense(category);
        newCourse.setCourseNumberHours(hours);
        courseDao.save(newCourse);
        model.put("SuccesUpdate", "Added New Course");

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);
        loadTemplate(writer, model, template);
    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load template manager-new-course");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template manager-new-course");
        }
    }
}
