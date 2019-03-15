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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "delete-instructor-to-student")
public class DeleteInstructorToStudentServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteInstructorToStudentServlet.class);
    private static final String TEMPLATE_NAME = "manager-student-delete-instructor";
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
        Student student = (Student) session.getAttribute("editedStudent");
        List<Instructor> instructorList = student.getInstructors();
        model.put("student", student);
        model.put("instructor", instructorList);

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
        Student student = (Student) session.getAttribute("editedStudent");
        model.put("user", managerSession);
        model.put("student", student);
        int instructorId = Integer.parseInt(req.getParameter("instructor"));
        List<Instructor> instructorList = student.getInstructors();

        for (int i = 0; i < instructorList.size(); i++) {
            if (instructorList.get(i).getInstructorId() == instructorId) {
                instructorList.remove(i);
                student.setInstructors(instructorList);
                studentDao.update(student);
                model.put("SuccesUpdate", "Delete Instructor for Student");
            }
        }
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);
        loadTemplate(writer, model, template);
    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load template manager-student-delete-instructor");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template manager-student-delete-instructor");
        }
    }
}
