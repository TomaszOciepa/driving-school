package servlet.manager;

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
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@WebServlet(urlPatterns = "details-student")
public class DetailsStudentServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DetailsStudentServlet.class);
    private static final String TEMPLATE_NAME = "manager-student-details";
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
        int id = Integer.parseInt(req.getParameter("id"));
        Student editedStudent = studentDao.findById(id);
        List<Course> courseList = editedStudent.getCourses();
        List<Instructor> instructorList = editedStudent.getInstructors();
        session.setAttribute("editedStudent", editedStudent);
        model.put("student", editedStudent);
        model.put("course", courseList);
        model.put("instructor", instructorList);

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);
        try {
            template.process(model, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
